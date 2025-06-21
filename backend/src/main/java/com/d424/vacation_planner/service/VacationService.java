package com.d424.vacation_planner.service;

import com.d424.vacation_planner.dao.VacationRepository;
import com.d424.vacation_planner.entity.Excursion;
import com.d424.vacation_planner.entity.User;
import com.d424.vacation_planner.entity.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;
    private ExcursionService excursionService;

    public Vacation save(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    public Vacation createVacation(Vacation vacation, User user) {
        vacation.setUser(user);
        return vacationRepository.save(vacation);
    }

    public Vacation updateVacation(Vacation vacation, User user) {
        vacation.setUser(user);
        return vacationRepository.save(vacation);
    }

    public List<Vacation> getVacationsByUser(User user){
        return vacationRepository.findByUser(user);
    }

    public List<Vacation> getVacationsByUserId(Long userId) {
        return vacationRepository.findByUserId(userId);
    }

    public Vacation getVacationById(Long vacationId){
        return vacationRepository.getVacationById(vacationId);
    }

    public List<Excursion> getExcursionsByVacationId(Long vacationId){
        return excursionService.getExcursionsByVacationId(vacationId);
    }

    public void deleteVacation(Long id){
        vacationRepository.deleteById(id);
    }
}
