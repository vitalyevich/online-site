package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
        Order findOrderById(int id);
        List<Order> findOrderByAccessId(int id);

}
