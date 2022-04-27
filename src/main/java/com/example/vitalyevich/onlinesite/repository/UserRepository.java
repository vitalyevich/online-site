package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById (int id);
}
