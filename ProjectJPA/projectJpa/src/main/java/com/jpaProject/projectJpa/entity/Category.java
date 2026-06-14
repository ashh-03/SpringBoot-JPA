package com.jpaProject.projectJpa.entity;

import com.jpaProject.projectJpa.baseClassEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
// no need to write this we used @MappedSuperClass
//@EntityListeners(
//        AuditingEntityListener.class
//)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotBlank(
            message = "Category name cannot be blank"
    )
    @Size(
            min = 3,
            max = 50,
            message = "Category name must be between 3 and 50 characters"
    )
    private String name;

    @Size(
            max = 255,
            message = "Description cannot exceed 255 characters"
    )
    private String description;

    @Version
    private Long version;


    @OneToMany(
            mappedBy = "category",

            cascade = CascadeType.ALL,

            orphanRemoval = true
    )

    private List<Product>
            products =
            new ArrayList<>();
}