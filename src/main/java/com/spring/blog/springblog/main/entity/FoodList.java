package com.spring.blog.springblog.main.entity;

import javax.persistence.*;

@Entity
public class FoodList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "FOOD_NAME")
    private String Food_Name;

    @Column(name = "FOOD_EXTENSION")
    private String Food_Extension;

    @Column(name = "FOOD_DESCRIPTION")
    private String Food_Description;

    @Column(name = "FOOD_INTRODUCE")
    private String Food_Introduce;


}