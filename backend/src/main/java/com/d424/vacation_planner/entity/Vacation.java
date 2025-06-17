package com.d424.vacation_planner.entity;

import com.d424.vacation_planner.validation.ValidVacationDates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ValidVacationDates
@Entity
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vacation title must not be blank")
    private String title;

    @NotBlank(message = "Start Date is required")
    private LocalDate startDate;

    @NotBlank(message = "End Date is required")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Excursion> excursions;

    // Getter and Setters
}
