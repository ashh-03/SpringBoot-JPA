package com.jpaProject.projectJpa.controller;

import com.jpaProject.projectJpa.dto.UserRequest;
import com.jpaProject.projectJpa.validations.groups.CreateGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/users")

public class UserController {

    @PostMapping

    public String createUser(

            @Validated(CreateGroup.class)

            @RequestBody

            UserRequest request
    ) {

        return "User Created";
    }



}