package com.jpaProject.projectJpa.inheritance.SingleTable;

import com.jpaProject.projectJpa.entity.Product;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@DiscriminatorValue(
        "PHYSICAL"
)

@Getter
@Setter
@NoArgsConstructor
public class PhysicalProduct
        extends Product {

    private Double weight;

    private Double shiingCost;
}