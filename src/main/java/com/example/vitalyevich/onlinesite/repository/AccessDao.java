package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.Access;
import com.example.vitalyevich.onlinesite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessDao extends JpaRepository<Access, Integer> {
    Access findByUsername(String username);
}
