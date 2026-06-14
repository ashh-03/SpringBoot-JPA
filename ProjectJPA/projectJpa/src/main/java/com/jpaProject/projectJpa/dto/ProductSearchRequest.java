package com.jpaProject.projectJpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchRequest {

    private String name;

    private String category;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Boolean inStock;
}