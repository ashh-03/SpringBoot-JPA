package com.jpaProject.projectJpa.entity;

import com.jpaProject.projectJpa.baseClassEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity


//-----------------using named query
@NamedQuery(
        name = "Product.findExpensiveProducts",

        query = """
                SELECT p
                FROM Product p
                WHERE p.price > :price
                """
)

//-----------------using named entity query

@NamedEntityGraph(
        name = "Product.withCategory",
        attributeNodes = {
                @NamedAttributeNode("category")
        }
)

//------------------------------------------------------------


@Table(name = "products")
// no need to write this we used @MappedSuperClass
//@EntityListeners(
//        AuditingEntityListener.class
//)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class Product extends BaseEntity {

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

    @Version
    private Long version;


    @OneToMany(
            mappedBy = "product",

            cascade = CascadeType.ALL,

            orphanRemoval = true
    )

    private List<Review>
            reviews =
            new ArrayList<>();
}