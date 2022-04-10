package com.example.vitalyevich.onlinesite.service;

import com.example.vitalyevich.onlinesite.model.Access;
import com.example.vitalyevich.onlinesite.model.User;

/**
 * Service class for {@link com.example.vitalyevich.onlinesite.model.User}
 *
 * @author Maksim Vitalyevich
 * @version 1.0
**/

public interface UserService {

    void save(Access user);

    Access findByUsername(String username);

}
