package com.jpaProject.projectJpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class Category {

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


    @OneToMany(
            mappedBy = "category",

            cascade = CascadeType.ALL,

            orphanRemoval = true
    )

    private List<Product>
            products =
            new ArrayList<>();
}