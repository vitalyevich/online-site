package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    Basket findBasketByProductId(int id);

    Iterable<Basket> findBasketByUserId(int id);

}
