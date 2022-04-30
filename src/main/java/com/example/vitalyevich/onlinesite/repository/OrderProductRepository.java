package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
}
