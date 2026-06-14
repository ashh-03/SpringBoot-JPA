package com.jpaProject.projectJpa.dto;

import com.jpaProject.projectJpa.entity.Product;

public class ProductMapper {

    public static ProductResponseDto
    toDto(Product product) {

        return new ProductResponseDto(

                product.getId(),

                product.getName(),

                product.getPrice(),

                product.getStockQuantity(),

                product.getCategory().getName()
        );
    }
}