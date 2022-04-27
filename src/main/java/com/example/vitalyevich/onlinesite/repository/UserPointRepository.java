package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPointRepository extends JpaRepository<UserPoint, Integer> {

    UserPoint findUserPointById(int id);
}
