package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.Point;
import com.example.vitalyevich.onlinesite.model.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Integer> {
}
