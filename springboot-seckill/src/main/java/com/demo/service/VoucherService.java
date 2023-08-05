package com.demo.service;

import com.demo.pojo.entity.Voucher;
import com.demo.pojo.entity.VoucherOrder;
import com.demo.pojo.vo.Result;
import com.demo.mapper.VoucherMapper;
import com.demo.mapper.VoucherOrderMapper;
import com.demo.utils.RedisIdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Collections;

import static com.demo.utils.RedisConstants.SECKILL_STOCK_KEY;

@Service
public class VoucherService {

    @Resource
    private VoucherMapper voucherMapper;

    @Resource
    private VoucherOrderMapper voucherOrderMapper;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;

    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;

    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("seckill.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }

    /**
     * 添加秒杀优惠券
     *
     * @param voucher
     */
    @Transactional
    public void save(Voucher voucher) {
        voucherMapper.save(voucher);
        stringRedisTemplate.opsForValue().set(SECKILL_STOCK_KEY + voucher.getId(), voucher.getStock().toString());
    }

    /**
     * v1
     * 无需登录访问, 不考虑一人一单
     * 只用 MySQL 实现秒杀，性能较差
     *
     * @param voucherId
     * @return
     */
    @Transactional
    public Result seckill_v1(Long voucherId) throws Exception {
        Voucher voucher = voucherMapper.findById(voucherId);
        if (voucher.getStock() < 1) {
            throw new Exception("库存不足");
        }

        Integer result = voucherMapper.deductStock(voucherId);
        if (result == 0) {
            throw new Exception("下单失败");
        }
        long orderId = redisIdWorker.nextId("order");
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setId(orderId);
        voucherOrder.setUserId(1L);
        voucherOrder.setVoucherId(voucherId);
        voucherOrderMapper.save(voucherOrder);
        return Result.success("下单成功", orderId);
    }

    /**
     * v2
     * 无需登录访问, 不考虑一人一单
     * 使用 Redis 扣库存，成功后使用 RabbitMQ 异步通知 MySQL 扣库存+保存订单
     *
     * @param voucherId
     * @return
     */
    public Result seckill_v2(Long voucherId) throws Exception {
        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString()
        );
        int r = result.intValue();
        if (r != 0) {
            throw new Exception("库存不足");
        }

        long orderId = redisIdWorker.nextId("order");
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setId(orderId);
        voucherOrder.setUserId(1L);
        voucherOrder.setVoucherId(voucherId);

        rabbitTemplate.convertAndSend("order.direct", "order", voucherOrder);

        return Result.success("下单成功", orderId);
    }
}
