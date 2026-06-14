package com.jpaProject.projectJpa.entity;



import com.jpaProject.projectJpa.baseClassEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "reviews")
// no need to write this we used @MappedSuperClass
//@EntityListeners(
//        AuditingEntityListener.class
//)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class Review extends BaseEntity {

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

    @Version
    private Long version;

    @ManyToOne(
            fetch = FetchType.LAZY
    )

    @JoinColumn(
            name = "product_id"
    )

    private Product product;
}
