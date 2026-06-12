package com.jpaProject.projectJpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity

@NamedQuery(
        name = "Product.findExpensiveProducts",

        query = """
                SELECT p
                FROM Product p
                WHERE p.price > :price
                """
)


@Table(name = "products")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotBlank(
            message = "Product name cannot be blank"
    )
    @Size(
            min = 3,
            max = 100
    )
    private String name;

    @Size(
            max = 500
    )
    private String description;

    @NotNull
    @Positive(
            message = "Price must be greater than zero"
    )
    private BigDecimal price;

    @NotNull
    @Min(
            value = 0,
            message = "Stock cannot be negative"
    )
    private Integer stockQuantity;


    @ManyToOne(
            fetch = FetchType.LAZY
    )

    @JoinColumn(
            name = "category_id"
    )

    private Category category;


    @OneToMany(
            mappedBy = "product",

            cascade = CascadeType.ALL,

            orphanRemoval = true
    )

    private List<Review>
            reviews =
            new ArrayList<>();
}