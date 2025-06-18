package com.d424.vacation_planner.config;

import com.d424.vacation_planner.entity.Excursion;
import com.d424.vacation_planner.entity.User;
import com.d424.vacation_planner.entity.Vacation;
import com.d424.vacation_planner.service.ExcursionService;
import com.d424.vacation_planner.service.UserService;
import com.d424.vacation_planner.service.VacationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(
            UserService userService,
            VacationService vacationService,
            ExcursionService excursionService) {
        return args -> {
            System.out.println("Initializing data...");

            // Check if sample exists
            String sampleUserEmail = "jane.doe@example.com";
            User sampleUser = userService.getUserByEmail(sampleUserEmail).orElse(null);

            if (sampleUser == null) {
                User user = new User();
                user.setFirstName("Jane");
                user.setLastName("Doe");
                user.setBirthDate(LocalDate.of(1990,6,15));
                user.setEmail("jane.doe@example.com");
                user.setPassword("jane.doe.password");

                userService.registerUser(user);
                System.out.println("Registered sample user");

                // Check if sample vacation & excursion exist
                if (vacationService.getVacationsByUser(user).isEmpty()) {
                    // Create sample vacation
                    Vacation vacation = new Vacation();
                    vacation.setTitle("Portugal");
                    vacation.setStartDate(LocalDate.of(2025, 7, 1));
                    vacation.setEndDate(LocalDate.of(2025, 7, 10));
                    vacation.setUser(user);

                    vacationService.save(vacation);
                    System.out.println("Saved vacation");

                    if (excursionService.getExcursionsByVacation(vacation).isEmpty()) {
                        // Create sample excursion
                        Excursion excursion = new Excursion();
                        excursion.setName("Montijo");
                        excursion.setStartDate(LocalDate.of(2025, 7, 4));
                        excursion.setVacation(vacation);

                        excursionService.saveExcursion(excursion);
                        System.out.println("Saved excursion");
                    } else {
                        System.out.println("Excursion already exists");
                    }
                } else {
                    System.out.println("Vacation already exists");
                }
            } else {
                System.out.println("Data initialized");
            }
        };
    }
}
