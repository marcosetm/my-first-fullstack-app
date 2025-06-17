package com.d424.vacation_planner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    // Fields
    @NotBlank(message = "First name cannot be blank")
    @Column(nullable = false)
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Column(nullable = false)
    private String lastName;

    private LocalDate birthDate;

    @Email
    @NotBlank(message = "Email cannot be blank")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Column(nullable = false)
    private String password;

    // Relationship
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacation> vacations;

    // Getters and Setters - use Lombok

}
