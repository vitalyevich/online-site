package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.*;
import com.example.vitalyevich.onlinesite.repository.*;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Pageable wholePage = Pageable.unpaged();
    private Category category = new Category();

    @GetMapping("/admin/clients")
    private String adminClient(Model model) {
        List<Access> accesses = accessRepository.findAll();
        model.addAttribute("accesses", accesses);
        return "work-clients";
    }

    @GetMapping("/admin/orders")
    private String adminOrder(Model model) {

        List<Order> orders = orderRepository.findAll();

        model.addAttribute("orders", orders);
        return "order-clients";
    }

    @PostMapping("/admin/orders/{id}")
    private String editStatus(Model model, @PathVariable(name = "id") int id,
                              @RequestParam(name = "status") String status) {

        Order order = orderRepository.findOrderById(id);
        order.setStatus(status);
        orderRepository.save(order);

        return "redirect:/admin/orders";
    }

    @GetMapping("/admin/main")
    private String adminMain(Model model) {
        return "admin-main";
    }

    @GetMapping({"/admin/menu", "/admin/menu/rolls"})
    private String menuRolls(Model model) {

        category.setId(1);
        Iterable<Product> rolls = productRepository.findByCategory(category,wholePage);
        model.addAttribute("menu", rolls);

        int rollsSize = 0;
        for (Object i : rolls) {
            rollsSize++;
        }
        model.addAttribute("size", rollsSize);

        return "admin-menu";
    }

    @GetMapping("/admin/menu/sushi")
    private String menuSushi(Model model) {

        category.setId(2);
        Iterable<Product> sushi = productRepository.findByCategory(category,wholePage);
        model.addAttribute("menu", sushi);

        int sushiSize = 0;
        for (Object i : sushi) {
            sushiSize++;
        }
        model.addAttribute("size", sushiSize);

        return "admin-menu";
    }

    @GetMapping( "/admin/menu/woks")
    private String menuWoks(Model model) {

        category.setId(3);
        Iterable<Product> woks = productRepository.findByCategory(category,wholePage);
        model.addAttribute("menu", woks);

        int woksSize = 0;
        for (Object i : woks) {
            woksSize++;
        }
        model.addAttribute("size", woksSize);

        return "admin-menu";
    }

    @GetMapping( "/admin/menu/soups")
    private String menuSoups(Model model) {

        category.setId(4);
        Iterable<Product> soups = productRepository.findByCategory(category,wholePage);
        model.addAttribute("menu", soups);

        int soupsSize = 0;
        for (Object i : soups) {
            soupsSize++;
        }
        model.addAttribute("size", soupsSize);

        return "admin-menu";
    }

    @GetMapping( "/admin/menu/sauces")
    private String menuSauces(Model model) {

        category.setId(5);
        Iterable<Product> sauces = productRepository.findByCategory(category,wholePage);
        model.addAttribute("menu", sauces);

        int saucesSize = 0;
        for (Object i : sauces) {
            saucesSize++;
        }
        model.addAttribute("size",saucesSize);

        return "admin-menu";
    }

    @GetMapping( "/admin/menu/drinks")
    private String menuDrinks(Model model) {

        category.setId(6);
        Iterable<Product> drinks = productRepository.findByCategory(category,wholePage);
        model.addAttribute("menu", drinks);

        int drinksSize = 0;
        for (Object i : drinks) {
            drinksSize++;
        }
        model.addAttribute("size",drinksSize);

        return "admin-menu";
    }

    @GetMapping( "/admin/menu/sets")
    private String menuSets(Model model) {

        category.setId(7);
        Iterable<Product> sets = productRepository.findByCategory(category,wholePage);
        model.addAttribute("menu", sets);

        int setsSize = 0;
        for (Object i : sets) {
            setsSize++;
        }
        model.addAttribute("size",setsSize);

        return "admin-menu";
    }


    @GetMapping("/admin/actions")
    private String adminAction(Model model) {

        Iterable<Action> actions = actionRepository.findAll();
        model.addAttribute("actions", actions);

        int size = 0;
        for (Object i : actions) {
            size++;
        }
        model.addAttribute("size",size);

        return "admin-actions";
    }

    @GetMapping("/admin/payments")
    private String adminPayment(Model model) {
        return "admin-payment";
    }

    @GetMapping("/admin/stats")
    private String adminStats(Model model) {
        return "admin-stats";
    }

    @GetMapping("/admin/program")
    private String adminLoyalty(Model model) {
        return "admin-program-loyalty";
    }

    @GetMapping( "/admin/points")
    private String adminPoints(Model model) {
        return "point-clients";
    }

}
