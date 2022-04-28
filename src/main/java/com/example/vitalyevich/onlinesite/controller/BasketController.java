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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BasketController {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccessRepository accessRepository;

    @PostMapping("/basket/add/{tag}/{id}")
    public String add(@PathVariable(value = "id") int id, @PathVariable(value = "tag") String tag) {

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


        if (tag.equals("menu")) {
            return "redirect:/menu/rolls";
        }
        if (tag.equals("basket")) {
            return "redirect:/basket";
        }

        return "redirect:/menu/rolls";
    }

    @PostMapping("/basket/sub/{tag}/{id}")
    public String sub(@PathVariable(value = "id") int id, @PathVariable(value = "tag") String tag) {

        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        Access userFromDb = accessRepository.findUserByPhone(phone);
        //

        Basket basket = basketRepository.findBasketByProductId(id);

        if (basket.getAmount() == 1) {
            basketRepository.delete(new Basket(new BasketId(userFromDb.getUser().getId(), id), new User(userFromDb.getUser().getId()), new Product(id), basket.getAmount()));
        } else {
            basket = new Basket(new BasketId(userFromDb.getUser().getId(), id), new User(userFromDb.getUser().getId()), new Product(id), basket.getAmount() - 1);
            basketRepository.save(basket);
        }


       /* if (tag.equals("menu")) {
            return "redirect:/menu/rolls";
        }*/
        if (tag.equals("basket")) {
            return "redirect:/basket";
        }

        return "redirect:/menu/rolls";
    }

    @PostMapping("/basket/del/{tag}/{id}")
    public String del(@PathVariable(value = "id") int id, @PathVariable(value = "tag") String tag) {

        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        Access userFromDb = accessRepository.findUserByPhone(phone);
        //

        Basket basket = basketRepository.findBasketByProductId(id);

        basketRepository.delete(new Basket(new BasketId(userFromDb.getUser().getId(), id), new User(userFromDb.getUser().getId()), new Product(id), basket.getAmount()));



        if (tag.equals("order")) {
            return "redirect:/order";
        }
        if (tag.equals("basket")) {
            return "redirect:/basket";
        }

        return "redirect:/menu/rolls";
    }

    @GetMapping("/basket")
    public String basket(Model model, RedirectAttributes rm) {

        return "redirect:/menu/rolls#blackout-basket";
    }

}
