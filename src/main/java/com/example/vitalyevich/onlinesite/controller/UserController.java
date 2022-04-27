package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.*;
import com.example.vitalyevich.onlinesite.repository.*;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
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

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
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
        return "redirect:/menu/rolls#blackout-registration";
    }

    @PostMapping("/registration")
    public String addUser(Access accessForm, User userForm, Model model) {
        Access userFromDb = accessRepository.findByPhone(accessForm.getPhone());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");

            return "redirect:/menu/rolls#blackout-registration";
        }

        if (!accessForm.getPassword().equals(accessForm.getConfirmPassword())) {
            return "redirect:/menu/rolls#blackout-registration";
        }

        accessForm.setActive(true);
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1));
        accessForm.setRoles(roles);

        // point create
        String generatedCode = RandomStringUtils.randomAlphanumeric(6);
        userForm.setUserCode(generatedCode.toUpperCase(Locale.ROOT));

        if (userForm.getInviteCode().equals("")) {
            userForm.setInviteCode(null);
        }
        if (userForm.getEmail().equals("")) {
            userForm.setEmail(null);
        }

        userRepository.save(userForm);

      //
        accessForm.setId(userForm.getId());
        accessForm.setUser(new User(userForm.getId()));
        accessRepository.save(accessForm);

        userPointRepository.save(new UserPoint(userForm.getId(),0));
//
        return "redirect:#blackout-authorization";
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
    public String saveSetting(Access accessSetting, User user,
                              Model model) {

        access.setPhone(accessSetting.getPhone());

        if (!accessSetting.getPassword().isEmpty() && !accessSetting.getConfirmPassword().isEmpty() ||
            !accessSetting.getPassword().equals("") && !accessSetting.getConfirmPassword().equals("")) {
            if (accessSetting.getPassword().equals(accessSetting.getConfirmPassword())) {

                access.setPassword(accessSetting.getPassword());

            } else {
                model.addAttribute("");
                return "settings";
            }
        }
        accessRepository.save(access);

        user.setId(access.getUser().getId());
        user.setUserCode(access.getUser().getUserCode());
        user.setInviteCode(access.getUser().getInviteCode());
        userRepository.save(user);

        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            String phone = SecurityContextHolder.getContext().getAuthentication().getName();

            Access userFromDb = accessRepository.findByPhone(phone);

            User user = userFromDb.getUser();

            UserPoint userPoint = userPointRepository.findUserPointById(user.getId());

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
}




