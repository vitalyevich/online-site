package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.Basket;
import com.example.vitalyevich.onlinesite.model.Product;
import com.example.vitalyevich.onlinesite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    Basket findBasketByProductId(int id);

    Iterable<Basket> findBasketByUserId(int id);
}
