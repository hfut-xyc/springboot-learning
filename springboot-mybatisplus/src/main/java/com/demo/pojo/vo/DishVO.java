package com.demo.pojo.vo;

import com.demo.pojo.entity.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishVO extends Dish {

    private String categoryName;

}
