package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.Access;
import com.example.vitalyevich.onlinesite.model.Role;
import com.example.vitalyevich.onlinesite.model.User;
/*import com.example.vitalyevich.onlinesite.service.SecurityService;
import com.example.vitalyevich.onlinesite.service.UserService;
import com.example.vitalyevich.onlinesite.validator.UserValidator;*/
import com.example.vitalyevich.onlinesite.repository.RoleDao;
import com.example.vitalyevich.onlinesite.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Controller for {@link User}'s pages.
 *
 * @author Maksim Vitalyevich
 * @version 1.0
**/

@Controller
@RequestMapping("/")
public class UserController {

/*    @GetMapping(value = "/welcome")
    public String registration(Model model) {

        return "welcome";
    }*/

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Access user, Model model) { //Map<String,Object> model
        Access userFromDb = userDao.findByUsername(user.getUsername());

        if (userFromDb != null) {
            //model.put("message", "User exists!");

            model.addAttribute("message", "User exists!");

            return "registration";
        }

        user.setActive(true);
        //user.setRoles(Collections.singleton(Role.));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1));
        user.setRoles(roles);
        //
        User user1 = new User();
        user1.setId(2);
        user.setUser(user1);
        //
        userDao.save(user);

        return "redirect:/login";
    }


  /*  @Autowired
    private UserService userService;*/

/*    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;*/

 /*   @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
      *//*  userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);*//*

        //securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }*/
}
