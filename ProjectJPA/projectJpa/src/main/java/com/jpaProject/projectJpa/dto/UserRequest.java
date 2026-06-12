package com.jpaProject.projectJpa.dto;


import com.jpaProject.projectJpa.validations.groups.CreateGroup;
import com.jpaProject.projectJpa.validations.groups.UpdateGroup;
import com.jpaProject.projectJpa.validations.payloads.Critical;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(
            message = "Name is required"

     , groups = {
        CreateGroup.class, UpdateGroup.class
    }
    )
    private String name;


    @Email(
            message = "Invalid email"
    )

    @NotBlank(
            message = "Email is required",

            payload = Critical.class
    )
    private String email;


    @Pattern(

            regexp = "^[0-9]{10}$",

            message =
                    "Phone must contain 10 digits"
    )
    private String phone;


    @NotBlank(

            groups =
                    CreateGroup.class,

            message =
                    "Password required during registration"
    )
    private String password;


    // getters setters
}