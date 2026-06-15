package com.jpaProject.projectJpa.entity;

import com.jpaProject.projectJpa.baseEntity_AutditorAware.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


//import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity

//----------soft delete-----------


//---------this is a old hibernate way...better add annotation @SoftDelete in BaseEntity class
//----------@SoftDelete do the same thing like we are doing here
//@SQLDelete(
//        sql =
//                "UPDATE product " +
//                        "SET deleted = true " +
//                        "WHERE id = ?"
//)
//---this is deprecated in hibernate 6+ uses filter[act as a filter and edit query before execute]
//@Where(
//        clause = "deleted = false"
//)


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


    //-------Locking Optimistic lock
    @Version
    private Long version;


    //---------soft delete-------
    private boolean deleted ;

    @OneToMany(
            mappedBy = "product",

            cascade = CascadeType.ALL,

            orphanRemoval = true
    )

    private List<Review>
            reviews =
            new ArrayList<>();
}