package com.demo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {

    private Long id;
    private Integer stock;
    private Date createTime;
    private Date updateTime;
}
