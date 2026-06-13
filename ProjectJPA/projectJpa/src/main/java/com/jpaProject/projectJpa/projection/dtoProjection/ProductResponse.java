package com.jpaProject.projectJpa.projection.dtoProjection;

import lombok.*;

import java.math.BigDecimal;

//@RequiredArgsConstructor
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ProductResponse {

    private  String name;

    private  BigDecimal price;

//    public ProductResponse(
//            String name,
//            BigDecimal price
//    ) {
//        this.name = name;
//        this.price = price;
//    }

//    public String getName() {
//        return name;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
}