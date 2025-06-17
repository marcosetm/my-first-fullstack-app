package com.d424.vacation_planner.service;

import com.d424.vacation_planner.dao.VacationRepository;
import com.d424.vacation_planner.entity.User;
import com.d424.vacation_planner.entity.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    public Vacation save(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    public Vacation createVacation(User user, Vacation vacation) {
        vacation.setUser(user);
        return vacationRepository.save(vacation);
    }

    public List<Vacation> getVacationsByUser(User user){
        return vacationRepository.findByUser(user);
    }

    public List<Vacation> getVacationsByUser(Long userId) {
        return vacationRepository.findByUserId(userId);
    }

    public Vacation getVacationById(Long id){
        return vacationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vacation with ID " + id + " not found"));
    }

    public void deleteVacation(Long id){
        vacationRepository.deleteById(id);
    }
}
