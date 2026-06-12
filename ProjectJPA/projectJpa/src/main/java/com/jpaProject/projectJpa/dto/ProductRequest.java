package com.jpaProject.projectJpa.dto;

import com.jpaProject.projectJpa.validation.annotation.ValidProductCode;

public class ProductRequest {

    @ValidProductCode

    private String productCode;

}