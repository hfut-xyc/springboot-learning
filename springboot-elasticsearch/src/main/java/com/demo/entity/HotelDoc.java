package com.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.io.Serializable;


@Data
@NoArgsConstructor
@Document(indexName = "hotel", createIndex = true)
public class HotelDoc implements Serializable {
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String all;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", copyTo = "all")
    private String name;

    @Field(type = FieldType.Keyword, index = false)
    private String address;

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Integer)
    private Integer score;

    @Field(type = FieldType.Keyword, copyTo = "all")
    private String brand;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Keyword)
    private String starName;

    @Field(type = FieldType.Keyword, copyTo = "all")
    private String business;

    @Field(type = FieldType.Keyword, index = false)
    private String pic;

    @GeoPointField
    private String location;

    public HotelDoc(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.price = hotel.getPrice();
        this.score = hotel.getScore();
        this.brand = hotel.getBrand();
        this.city = hotel.getCity();
        this.starName = hotel.getStarName();
        this.business = hotel.getBusiness();
        this.location = hotel.getLatitude() + ", " + hotel.getLongitude();
        this.pic = hotel.getPic();
    }
}
