package com.demo.dao;

import com.demo.entity.HotelDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends ElasticsearchRepository<HotelDoc, Integer> {

}
