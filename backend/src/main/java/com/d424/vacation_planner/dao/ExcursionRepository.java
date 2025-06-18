package com.d424.vacation_planner.dao;

import com.d424.vacation_planner.entity.Excursion;
import com.d424.vacation_planner.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
    List<Excursion> findAllByVacation(Vacation vacation);
    List<Excursion> findByVacationId(Long vacationId);
    Excursion getExcursionById(Long excursionId);
}
