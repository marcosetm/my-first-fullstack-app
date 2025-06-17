package com.d424.vacation_planner.dao;

import com.d424.vacation_planner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findById(long id);
}
