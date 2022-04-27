package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.*;
import com.example.vitalyevich.onlinesite.repository.AccessRepository;
import com.example.vitalyevich.onlinesite.repository.BasketRepository;
import com.example.vitalyevich.onlinesite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BasketController {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccessRepository accessRepository;

    @PostMapping("/basket/add/{id}")
    public String add(@PathVariable(value = "id") int id) {

        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        Access userFromDb = accessRepository.findUserByPhone(phone);
        //

        Basket basket = basketRepository.findBasketByProductId(id);

        if (basket != null) {
            basket = new Basket(new BasketId(userFromDb.getUser().getId(), id),new User(userFromDb.getUser().getId()),new Product(id),basket.getAmount() + 1);
        } else {
            basket = new Basket(new BasketId(userFromDb.getUser().getId(), id),new User(userFromDb.getUser().getId()),new Product(id),1);
        }
        basketRepository.save(basket);

        return "redirect:/menu/rolls";
    }

    @GetMapping("/basket") // фикс с id пользователя
    public String basket(Model model) {

        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        Access userFromDb = accessRepository.findUserByPhone(phone);
        //

        Iterable<Basket> baskets = basketRepository.findBasketByUserId(userFromDb.getUser().getId());

        model.addAttribute("baskets", baskets);
        model.addAttribute("id", userFromDb.getUser().getId());
        return "redirect:/menu/rolls#blackout-basket";
    }

}
