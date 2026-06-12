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
@Table(name="users")
public class User {

    @Id

    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String name;

    private String email;

    private String phone;

    @OneToOne(mappedBy = "user" , cascade =
            CascadeType.ALL ,
            fetch =
                    FetchType.LAZY)
    private Address address;
}