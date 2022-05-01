package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.Address;
import com.example.vitalyevich.onlinesite.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Override
    List<Address> findAll();

    List<Address> findAddressByUsers(User user);
}
