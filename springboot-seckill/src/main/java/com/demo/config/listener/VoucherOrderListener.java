package com.demo.config.listener;

import com.demo.entity.VoucherOrder;
import com.demo.mapper.VoucherMapper;
import com.demo.mapper.VoucherOrderMapper;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class VoucherOrderListener {

    @Resource
    private VoucherMapper voucherMapper;

    @Resource
    private VoucherOrderMapper voucherOrderMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "order.queue"),
            exchange = @Exchange(name = "order.direct"),
            key = {"order"})
    )
    @Transactional
    public void orderListener(VoucherOrder order) {
        System.out.println(order);
        // mysql中扣减库存
        voucherMapper.deductStock(order.getVoucherId());
        // 创建订单
        voucherOrderMapper.save(order);
    }
}
