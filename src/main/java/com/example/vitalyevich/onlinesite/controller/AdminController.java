package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.Category;
import com.example.vitalyevich.onlinesite.model.Product;
import com.example.vitalyevich.onlinesite.repository.ProductRepository;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.StreamSupport;

@Controller
public class AdminController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/admin/panel")
    private String adminPanel(Model model) {
        Pageable wholePage = Pageable.unpaged();
        Category category = new Category();

        category.setId(1);
        Iterable<Product> rolls = productRepository.findByCategory(category,wholePage);
        model.addAttribute("rolls", rolls);

        int rollsSize = 0;
        for (Object i : rolls) {
            rollsSize++;
        }
        model.addAttribute("rollsSize", rollsSize);

        category.setId(2);
        Iterable<Product> sushi = productRepository.findByCategory(category,wholePage);
        model.addAttribute("sushi", sushi);

        int sushiSize = 0;
        for (Object i : sushi) {
            sushiSize++;
        }
        model.addAttribute("sushiSize", sushiSize);

        category.setId(3);
        Iterable<Product> woks = productRepository.findByCategory(category,wholePage);
        model.addAttribute("woks", woks);

        int woksSize = 0;
        for (Object i : woks) {
            woksSize++;
        }
        model.addAttribute("woksSize", woksSize);

        category.setId(4);
        Iterable<Product> soups = productRepository.findByCategory(category,wholePage);
        model.addAttribute("soups", soups);

        int soupsSize = 0;
        for (Object i : soups) {
            soupsSize++;
        }
        model.addAttribute("soupsSize", soupsSize);

        category.setId(5);
        Iterable<Product> sauces = productRepository.findByCategory(category,wholePage);
        model.addAttribute("sauces", sauces);

        int saucesSize = 0;
        for (Object i : sauces) {
            saucesSize++;
        }
        model.addAttribute("saucesSize",saucesSize);

        category.setId(6);
        Iterable<Product> drinks = productRepository.findByCategory(category,wholePage);
        model.addAttribute("drinks", drinks);

        int drinksSize = 0;
        for (Object i : drinks) {
            drinksSize++;
        }
        model.addAttribute("drinksSize",drinksSize);

        category.setId(7);
        Iterable<Product> sets = productRepository.findByCategory(category,wholePage);
        model.addAttribute("sets", sets);

        int setsSize = 0;
        for (Object i : sets) {
            setsSize++;
        }
        model.addAttribute("setsSize",setsSize);

        return "admin-panel";
    }

}
