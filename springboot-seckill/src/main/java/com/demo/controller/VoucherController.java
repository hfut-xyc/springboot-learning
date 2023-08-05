package com.demo.controller;

import com.demo.pojo.entity.Voucher;
import com.demo.pojo.vo.Result;
import com.demo.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @Resource
    private VoucherService voucherService;

    @PostMapping
    public void addVoucher(@RequestBody Voucher voucher) {
        voucherService.save(voucher);
    }

    @PostMapping("/seckill/v1")
    public Result seckill1(@RequestParam Long voucherId) throws Exception {
        log.debug("/seckill/v1/{}", voucherId);
        return voucherService.seckill_v1(voucherId);
    }

    @PostMapping("/seckill/v2")
    public Result seckill2(@RequestParam Long voucherId) throws Exception {
        log.debug("/seckill/v2/{}", voucherId);
        return voucherService.seckill_v2(voucherId);
    }
}
