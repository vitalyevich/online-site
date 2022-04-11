/*
package com.example.vitalyevich.onlinesite.service;

import com.example.vitalyevich.onlinesite.model.Access;
import com.example.vitalyevich.onlinesite.model.Role;
import com.example.vitalyevich.onlinesite.repository.RoleDao;
import com.example.vitalyevich.onlinesite.repository.AccessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

*/
/**
 * Implementation of {@link com.example.vitalyevich.onlinesite.service.UserService} interface.
 *
 * @author Maksim Vitalyevich
 * @version 1.0
**//*


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccessDao userDao;

    @Autowired
    private RoleDao roleDao;

// @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Access user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1));
        user.setRoles(roles);

        userDao.save(user);
    }

    @Override
    public Access findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
*/
