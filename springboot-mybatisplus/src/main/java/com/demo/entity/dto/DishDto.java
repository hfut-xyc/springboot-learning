package com.demo.entity.dto;

import com.demo.entity.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto extends Dish {

    private String categoryName;

}
