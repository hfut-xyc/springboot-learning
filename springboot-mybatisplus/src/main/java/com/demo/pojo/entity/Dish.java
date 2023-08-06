package com.demo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @date 2022-9-18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish implements Serializable {

    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
