package com.sangwon.springbootjpapostgresql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username is required")
    @Size(max = 50, message = "Username must be less than or equal to 50 characters")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @NotEmpty(message = "Email is required")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty(message = "First name is required")
    @Size(max = 50, message = "First name must be less than or equal to 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
}
