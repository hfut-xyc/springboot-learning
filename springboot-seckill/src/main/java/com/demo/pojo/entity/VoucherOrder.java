package com.demo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherOrder {

    private Long id;
    private Long userId;
    private Long voucherId;
    private Date createTime;
    private Date updateTime;

}
