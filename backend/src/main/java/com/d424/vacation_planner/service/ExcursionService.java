package com.d424.vacation_planner.service;

import com.d424.vacation_planner.dao.ExcursionRepository;
import com.d424.vacation_planner.entity.Excursion;
import com.d424.vacation_planner.entity.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcursionService {

    @Autowired
    private ExcursionRepository excursionRepository;

    public Excursion saveExcursion(Excursion excursion) {
        return excursionRepository.save(excursion);
    }
    public Excursion createExcursion(Vacation vacation, Excursion excursion) {
        excursion.setVacation(vacation);
        return excursionRepository.save(excursion);
    }

    public List<Excursion> getExcursionsByVacation(Vacation vacation){
        return excursionRepository.findAllByVacation(vacation);
    }

    public Optional<Excursion> findByVacationId(Long id){
        return excursionRepository.findById(id);
    }

    public List<Excursion> getExcursionsByVacationId(Long vacationId) {
        return excursionRepository.findByVacationId(vacationId);
    }

    public void deleteExcursionById(Long id){
        excursionRepository.deleteById(id);
    }
}
