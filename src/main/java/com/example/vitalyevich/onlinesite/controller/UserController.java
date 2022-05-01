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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/registration")
    public String registration() {
        return "redirect:/menu/rolls#blackout-registration";
    }

    @PostMapping("/registration")
    public String addUser(Access accessForm, User userForm, Model model, RedirectAttributes rm) {
        Access userFromDb = accessRepository.findByPhone(accessForm.getPhone());

        if (userFromDb != null) {
            rm.addFlashAttribute("message", "Данный номер телефона уже используется!");

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

    @GetMapping("/selection")
    public String selection() {
        return "menu-selection";
    }


    @GetMapping("/modal")
    public String modal() {
        return "redirect:/profile#blackout-modal";
    }

    @GetMapping("/delivery")
    public String delivery() {
        return "delivery";
    }

    @GetMapping("/profile/orders")
    public String orders(Model model) {
        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        access = accessRepository.findByPhone(phone);
        //

        List<Order> orders = orderRepository.findOrderByAccessId(access.getId());

        model.addAttribute("orders", orders);

        return "client-orders";
    }

    @GetMapping("/profile/address")
    public String address(Model model) {
        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        access = accessRepository.findByPhone(phone);
        //


        List<Address> addresses = addressRepository.findAddressByUsers(new User(access.getUser().getId()));

        model.addAttribute("address", addresses);

        return "client-address";
    }

    @PostMapping({"/profile/address/save/{id}", "/profile/address/save"})
    public String saveAddress(Address address,@RequestParam(name = "cityName") int cityId, @PathVariable(required = false) Integer id) {
        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        access = accessRepository.findByPhone(phone);
        //

        List<User> users = new ArrayList<>();
        users.add(access.getUser());
        address.setCity(new City(cityId));
        Set<User> userSet = new HashSet<>(users);
        address.setUsers(userSet);
        addressRepository.save(address);

       return "redirect:/profile/address";
    }

    @GetMapping("/profile/address/del/{id}")
    public String delAddress(Model model, @PathVariable(required = false) Integer id) {
        
        addressRepository.delete(new Address(id));
        return "redirect:/profile/address";
    }
}




