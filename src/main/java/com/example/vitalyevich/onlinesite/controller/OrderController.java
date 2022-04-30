package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.*;
import com.example.vitalyevich.onlinesite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Controller
public class OrderController {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private UserPointRepository userPointRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository productRepository;

    @GetMapping("/order")
    private String order(Model model) {

        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        Access userFromDb = accessRepository.findUserByPhone(phone);
        //

        UserPoint userPoint = userPointRepository.findUserPointById(userFromDb.getUser().getId());


        Iterable<Basket> baskets = basketRepository.findBasketByUserId(userFromDb.getUser().getId());
        model.addAttribute("baskets", baskets);

        List<Basket> basketList = new ArrayList<Basket>();
        baskets.forEach(basketList::add);

        if (basketList.size() == 0) {
            return "redirect:menu/rolls";
        }

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
        model.addAttribute("balance", userPoint.getBalance());
        return "order";
    }
    @PostMapping("/order")
    private String add(Model model, @RequestParam(name = "address") String address,
                       @RequestParam(name = "h") int h,
                       @RequestParam(name = "m") int m,
                       @RequestParam(name = "d") int date,
                       @RequestParam(name= "contact") int payment,
                       @RequestParam(name = "point") int point,
                       @RequestParam(name = "comment") String comment) {
        //
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        Access userFromDb = accessRepository.findUserByPhone(phone);

        User user = userFromDb.getUser();
        //

        Iterable<Basket> baskets = basketRepository.findBasketByUserId(userFromDb.getUser().getId());
        List<Basket> basketList = new ArrayList<>();
        baskets.forEach(basketList::add);
        List<OrderProduct> orderProducts = new ArrayList<>();

        for (Basket basket: basketList) {
            orderProducts.add(new OrderProduct(basket.getProduct(),basket.getAmount()));
        }


        Set<OrderProduct> productSet = new HashSet<>(orderProducts);


        Order order = new Order();

        Address addressClient = new Address();
        addressClient.setId(1);
        order.setAddress(addressClient);
        order.setType("Самовывоз");
        order.setEndDate(Instant.now());
        order.setOrderDate(Instant.now());

        if (comment.equals("")) {
            order.setComment(null);
        }
        else {
            order.setComment(comment);
        }

        Payment paymentClient = new Payment();
        paymentClient.setId(payment);

        order.setPayment(paymentClient);

        order.setPrice(500.0);
        order.setAccess(userFromDb);

        orderRepository.save(order);

        for (Basket basket: basketList) {
            productRepository.save(new OrderProduct(order,basket.getProduct(),basket.getAmount()));
            basketRepository.delete(basket);
        }

        return "redirect:/profile";
    }

    @GetMapping("/profile/orders/cancel/{id}")
    private String cancelOrder(Model model, @PathVariable(name = "id") int id) {

        Order order = orderRepository.findOrderById(id);

        if (order.getStatus().equals("Новый") || order.getStatus().equals("В очереди")) {
            order.setStatus("Отмена");
            orderRepository.save(order);
        }

        return "redirect:/profile/orders";
    }
}
