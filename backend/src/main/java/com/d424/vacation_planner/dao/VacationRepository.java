package com.d424.vacation_planner.dao;

import com.d424.vacation_planner.entity.User;
import com.d424.vacation_planner.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    List<Vacation> findByUser(User user);
    List<Vacation> findByUserId(Long userId);
    Vacation getVacationById(Long vacationId);
}
