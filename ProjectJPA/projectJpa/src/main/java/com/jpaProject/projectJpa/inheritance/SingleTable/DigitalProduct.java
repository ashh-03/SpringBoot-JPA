package com.jpaProject.projectJpa.inheritance.SingleTable;

import com.jpaProject.projectJpa.entity.Product;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@DiscriminatorValue(
        "DIGITAL"
)

@Getter
@Setter
@NoArgsConstructor
public class DigitalProduct
        extends Product {

    private String downloadUrl;

    private String licenseKey;
}