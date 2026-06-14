package com.jpaProject.projectJpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductResponseDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stockQuantity;

    private String categoryName;
}