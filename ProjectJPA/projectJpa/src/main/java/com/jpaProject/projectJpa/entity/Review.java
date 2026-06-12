package com.jpaProject.projectJpa.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "reviews")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class Review {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotNull
    @Min(
            value = 1,
            message = "Rating must be at least 1"
    )
    @Max(
            value = 5,
            message = "Rating cannot exceed 5"
    )
    private Integer rating;

    @NotBlank(
            message = "Comment cannot be blank"
    )
    @Size(
            max = 500
    )
    private String comment;

    @ManyToOne(
            fetch = FetchType.LAZY
    )

    @JoinColumn(
            name = "product_id"
    )

    private Product product;
}
