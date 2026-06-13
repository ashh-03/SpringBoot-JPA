package com.jpaProject.projectJpa.projection.dtoProjection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class ProductCustomResponse {



        private String name;

        private BigDecimal price;

        private boolean inStock;

        public ProductCustomResponse(
                String name,
                BigDecimal price,
                Integer stockQuantity
        ) {

            this.name = name;
            this.price = price;

            this.inStock =
                    stockQuantity > 0;
        }


//        @Getter
//         private boolean isInStock;
    }


