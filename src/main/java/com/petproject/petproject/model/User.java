package com.petproject.petproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "security",schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Roles roles;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Roles getRoles() {
        return roles;
    }



}
