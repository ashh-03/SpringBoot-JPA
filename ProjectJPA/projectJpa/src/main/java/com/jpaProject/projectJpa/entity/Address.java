package com.jpaProject.projectJpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="address")
public class Address {

    @Id

    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(
            name = "user_id"
    )

    private User user;
}