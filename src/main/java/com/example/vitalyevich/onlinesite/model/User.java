package com.example.vitalyevich.onlinesite.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_code", nullable = false, length = 6)
    private String userCode;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "invite_code", length = 6)
    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String userCode, String firstName, LocalDate dateOfBirth, String email, String inviteCode) {
        this.userCode = userCode;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.inviteCode = inviteCode;
    }

    public User(String userCode, String firstName) {
        this.userCode = userCode;
        this.firstName = firstName;
    }

    public User() {
    }
}