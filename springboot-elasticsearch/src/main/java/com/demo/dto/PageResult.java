package com.demo.dto;

import com.demo.entity.HotelDoc;
import lombok.Data;

import java.util.List;

/**
 * @date 2022-9-30
 **/
@Data
public class PageResult<T> {
    private Long total;
    private List<T> hotels;

    public PageResult(Long total, List<T> hotels) {
        this.total = total;
        this.hotels = hotels;
    }
}