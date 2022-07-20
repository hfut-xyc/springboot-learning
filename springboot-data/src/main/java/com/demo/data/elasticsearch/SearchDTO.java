package com.demo.data.elasticsearch;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchDTO implements Serializable {

    private String keyword;
    private Integer page;
    private Integer pageSize;
}
