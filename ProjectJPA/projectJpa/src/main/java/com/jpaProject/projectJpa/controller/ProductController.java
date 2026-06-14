package com.jpaProject.projectJpa.controller;

import com.jpaProject.projectJpa.dto.ProductResponseDto;
import com.jpaProject.projectJpa.dto.ProductSearchRequest;
import com.jpaProject.projectJpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/search")
    public Page<ProductResponseDto> searchProducts(

            @RequestBody
            ProductSearchRequest request,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction

    ) {

        return productService.searchProducts(

                request,

                page,

                size,

                sortBy,

                direction
        );
    }
}