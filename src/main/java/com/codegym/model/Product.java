package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;

    private String name;

    private String information;

    private double price;

    private String priceFormatted;

    public Product(Long id, String name, String information, double price) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.price = price;
        this.priceFormatted = String.format("%,.0f VND", price);
    }

}
