package com.jpaProject.projectJpa.inheritance.SingleTable;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity

@Inheritance(
        strategy =
                InheritanceType.SINGLE_TABLE
)

@DiscriminatorColumn(
        name = "product_type"
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class HeadClassProduct {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long id;

    private String name;

    private BigDecimal price;
}