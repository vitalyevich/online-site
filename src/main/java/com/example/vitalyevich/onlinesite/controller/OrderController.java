package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.Access;
import com.example.vitalyevich.onlinesite.model.Basket;
import com.example.vitalyevich.onlinesite.repository.AccessRepository;
import com.example.vitalyevich.onlinesite.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private AccessRepository accessRepository;

    @GetMapping("/order")
    private String order(Model model) {

        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        Access userFromDb = accessRepository.findUserByPhone(phone);
        //

        Iterable<Basket> baskets = basketRepository.findBasketByUserId(userFromDb.getUser().getId());
        model.addAttribute("baskets", baskets);

        List<Basket> basketList = new ArrayList<Basket>();
        baskets.forEach(basketList::add);

        float sum = 0;
        float result = 0;
        int delivery = 0;
        for (Basket b : basketList) {
            sum += b.getAmount() * Double.parseDouble(b.getProduct().getPrice().getPrice());
        }

        if (sum >= 20) {
            model.addAttribute("delivery", 0);
            result = sum;
        } else {
            model.addAttribute("delivery", 7);
            delivery = 7;
            result = sum + delivery;
        }


        model.addAttribute("sum",sum);
        model.addAttribute("result",result);
        model.addAttribute("delivery",delivery);
        return "order";
    }
}
