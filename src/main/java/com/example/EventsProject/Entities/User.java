package com.example.EventsProject.Entities;

import com.example.EventsProject.Enums.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class User {
    private Long id;

    private String name;

    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}

