package com.jpaProject.projectJpa.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity

@Table(name = "orders")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id

    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private String status;
}