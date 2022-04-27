package com.example.vitalyevich.onlinesite.model;

import javax.persistence.*;

@Entity
@Table(name = "user_points")
public class UserPoint {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "balance", nullable = false)
    private Integer balance;

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public UserPoint() {
    }

    public UserPoint(Integer id, Integer balance) {
        this.id = id;
        this.balance = balance;
    }
}