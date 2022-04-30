package com.example.onlineshoppingdemo.dto;
import lombok.*;



@Data
public class ProductDto   {

    private Integer id;
    private String name;
    private double price;
    private String description;
    private String image;
    private int categoryId;



}
