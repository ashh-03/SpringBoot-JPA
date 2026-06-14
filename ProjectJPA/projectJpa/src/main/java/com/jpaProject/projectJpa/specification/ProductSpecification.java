package com.jpaProject.projectJpa.specification;

import com.jpaProject.projectJpa.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product>
    hasName(String name) {

        return (root, query, cb) ->

                cb.like(

                        cb.lower(
                                root.get("name")
                        ),

                        "%" + name.toLowerCase() + "%"
                );
    }

    public static Specification<Product>
    hasCategory(String category) {

        return (root, query, cb) ->

                cb.equal(

                        root
                                .get("category")
                                .get("name"),

                        category
                );
    }

    public static Specification<Product>
    minPrice(BigDecimal minPrice) {

        return (root, query, cb) ->

                cb.greaterThanOrEqualTo(

                        root.get("price"),

                        minPrice
                );
    }

    public static Specification<Product>
    maxPrice(BigDecimal maxPrice) {

        return (root, query, cb) ->

                cb.lessThanOrEqualTo(

                        root.get("price"),

                        maxPrice
                );
    }

    public static Specification<Product>
    inStock() {

        return (root, query, cb) ->

                cb.greaterThan(

                        root.get("stockQuantity"),

                        0
                );
    }
}