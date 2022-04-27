package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.*;
import com.example.vitalyevich.onlinesite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Controller for {@link User}'s pages.
 *
 * @author Maksim Vitalyevich
 * @version 1.0
**/

@Controller
public class UserController {

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserPointRepository userPointRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/authorization")
    public String authorization() {
        return "redirect:/menu/rolls#blackout-authorization";
    }

    private Access access = new Access();

    @GetMapping("/settings")
    public String settings(Model model) {
        Page<Product> page = productRepository.findAll(PageRequest.of(0, 4));
        Iterable<Product> products = page;

        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        access = accessRepository.findByPhone(phone);
        //

        model.addAttribute("products", products);
        model.addAttribute("user", access);
        return "settings";
    }

    @PostMapping("/settings")
    public String saveSetting(Access access, User user,
                              Model model) {

        return "settings";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            String phone = SecurityContextHolder.getContext().getAuthentication().getName();

            Access userFromDb = accessRepository.findByPhone(phone);

            User user = userFromDb.getUser();

            UserPoint userPoint = userPointRepository.findUserPointByUserId(user.getId());

            model.addAttribute("points", userPoint.getBalance());
            model.addAttribute("user", user);

            Page<Product> page = productRepository.findAll(PageRequest.of(0, 4));
            Iterable<Product> products = page;
            model.addAttribute("products", products);

            return "profile";
        }
        else {
            return "redirect:#blackout-authorization";
        }
    }



    @GetMapping("/admin-panel")
    public String adminPanel() {
        return "admin-panel";
    }

    @GetMapping("/selection")
    public String selection() {
        return "menu-selection";
    }

    @GetMapping("/delivery")
    public String delivery() {
        return "delivery";
    }

    @PostMapping("/registration")
    public String addUser(Access access, Model model) {
        Access userFromDb = accessRepository.findByPhone(access.getPhone());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");

            return "registration";
        }

        access.setActive(true);
        //user.setRoles(Collections.singleton(Role.));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1));
        access.setRoles(roles);
        //
        User user1 = new User();
        user1.setId(2);
        access.setUser(user1);
        //
        accessRepository.save(access);

        return "redirect:/login";
    }
}

  /*  @Autowired
    private UserService userService;*/

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
}
*/
