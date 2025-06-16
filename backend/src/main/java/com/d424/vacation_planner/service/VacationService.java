package com.d424.vacation_planner.service;

import com.d424.vacation_planner.dao.VacationRepository;
import com.d424.vacation_planner.entity.User;
import com.d424.vacation_planner.entity.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    public Vacation save(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    public List<Vacation> getVacationsByUser(User user){
        return vacationRepository.findByUser(user);
    }

    public Optional<Vacation> getVacationById(Long id){
        return vacationRepository.findById(id);
    }

    public void deleteVacation(Long id){
        vacationRepository.deleteById(id);
    }
}
