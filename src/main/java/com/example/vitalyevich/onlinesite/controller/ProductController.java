package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.Access;
import com.example.vitalyevich.onlinesite.model.Basket;
import com.example.vitalyevich.onlinesite.model.Category;
import com.example.vitalyevich.onlinesite.model.Product;
import com.example.vitalyevich.onlinesite.repository.AccessRepository;
import com.example.vitalyevich.onlinesite.repository.BasketRepository;
import com.example.vitalyevich.onlinesite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private BasketRepository basketRepository;

    private Category category = new Category();


    @GetMapping({"/menu/rolls/page/{offset}", "/menu/rolls"})
    public String menuRolls(Model model, @PathVariable(required = false) Integer offset) {

        // корзина
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            String phone = SecurityContextHolder.getContext().getAuthentication().getName();
            Access userFromDb = accessRepository.findUserByPhone(phone);
            //

            Iterable<Basket> baskets = basketRepository.findBasketByUserId(userFromDb.getUser().getId());

            List<Basket> basketList = new ArrayList<Basket>();
            baskets.forEach(basketList::add);
            model.addAttribute("baskets", baskets);


            float sum = 0;
            float result = 0;
            int delivery = 0;
            for (Basket b : basketList) {
                sum += b.getAmount() * Double.parseDouble(b.getProduct().getPrice().getPrice());
            }

            model.addAttribute("sum", String.format("%.1f",sum).replace(',','.'));
            model.addAttribute("number", basketList.size());
        }
        //


        if (offset == null) {
            offset = 0;
        }
        if (offset < 0) {
            return "redirect:/menu/rolls";
        }

        category.setId(1);
        Page<Product> page = productRepository.findByCategory(category,PageRequest.of(offset, 6));
        Iterable<Product> products = page;

        if (offset >= page.getTotalPages()) {
            return "redirect:/menu/rolls/page/"+ (page.getTotalPages() - 1);
        }

        model.addAttribute("page", page);

        model.addAttribute("products", products);

        model.addAttribute("url", "/menu/rolls/page/");
        model.addAttribute("menu", "Меню роллов");

        return "menu";
    }

    @GetMapping({"/menu/sushi/page/{offset}", "/menu/sushi"})
    public String menuSushi(Model model, @PathVariable(required = false) Integer offset) {

        if (offset == null) {
            offset = 0;
        }
        if (offset < 0) {
            return "redirect:/menu/sushi";
        }

        category.setId(2);
        Page<Product> page = productRepository.findByCategory(category,PageRequest.of(offset, 6));
        Iterable<Product> products = page;

        if (offset >= page.getTotalPages()) {
            return "redirect:/menu/sushi/page/"+ (page.getTotalPages() - 1);
        }

        model.addAttribute("page", page);

        model.addAttribute("products", products);

        model.addAttribute("url", "/menu/sushi/page/");
        model.addAttribute("menu", "Меню суш");

        return "menu";
    }

    @GetMapping({"/menu/woks/page/{offset}", "/menu/woks"})
    public String menuWoks(Model model, @PathVariable(required = false) Integer offset) {

        if (offset == null) {
            offset = 0;
        }
        if (offset < 0) {
            return "redirect:/menu/woks";
        }

        category.setId(3);
        Page<Product> page = productRepository.findByCategory(category,PageRequest.of(offset, 6));
        Iterable<Product> products = page;

        if (offset >= page.getTotalPages()) {
            return "redirect:/menu/woks/page/"+ (page.getTotalPages() - 1);
        }

        model.addAttribute("page", page);

        model.addAttribute("products", products);

        model.addAttribute("url", "/menu/woks/page/");
        model.addAttribute("menu", "Меню воков");

        return "menu";
    }

    @GetMapping({"/menu/soups/page/{offset}", "/menu/soups"})
    public String menuSoups(Model model, @PathVariable(required = false) Integer offset) {

        if (offset == null) {
            offset = 0;
        }
        if (offset < 0) {
            return "redirect:/menu/soups";
        }

        category.setId(4);
        Page<Product> page = productRepository.findByCategory(category,PageRequest.of(offset, 6));
        Iterable<Product> products = page;

        if (offset >= page.getTotalPages()) {
            return "redirect:/menu/soups/page/"+ (page.getTotalPages() - 1);
        }

        model.addAttribute("page", page);

        model.addAttribute("products", products);

        model.addAttribute("url", "/menu/soups/page/");
        model.addAttribute("menu", "Меню супов");

        return "menu";
    }

    @GetMapping({"/menu/sauces/page/{offset}", "/menu/sauces"})
    public String menuSauces(Model model, @PathVariable(required = false) Integer offset) {

        if (offset == null) {
            offset = 0;
        }
        if (offset < 0) {
            return "redirect:/menu/sauces";
        }

        category.setId(5);
        Page<Product> page = productRepository.findByCategory(category,PageRequest.of(offset, 6));
        Iterable<Product> products = page;

        if (offset >= page.getTotalPages()) {
            return "redirect:/menu/sauces/page/"+ (page.getTotalPages() - 1);
        }

        model.addAttribute("page", page);

        model.addAttribute("products", products);

        model.addAttribute("url", "/menu/sauces/page/");
        model.addAttribute("menu", "Меню соусов");

        return "menu";
    }

    @GetMapping({"/menu/drinks/page/{offset}", "/menu/drinks"})
    public String menuDrinks(Model model, @PathVariable(required = false) Integer offset) {

        if (offset == null) {
            offset = 0;
        }
        if (offset < 0) {
            return "redirect:/menu/drinks";
        }

        category.setId(6);
        Page<Product> page = productRepository.findByCategory(category,PageRequest.of(offset, 6));
        Iterable<Product> products = page;

        if (offset >= page.getTotalPages()) {
            return "redirect:/menu/drinks/page/"+ (page.getTotalPages() - 1);
        }

        model.addAttribute("page", page);

        model.addAttribute("products", products);

        model.addAttribute("url", "/menu/drinks/page/");
        model.addAttribute("menu", "Меню напитков");

        return "menu";
    }

    @GetMapping({"/menu/sets/page/{offset}", "/menu/sets"})
    public String menuSets(Model model, @PathVariable(required = false) Integer offset) {

        if (offset == null) {
            offset = 0;
        }
        if (offset < 0) {
            return "redirect:/menu/sets";
        }

        category.setId(7);
        Page<Product> page = productRepository.findByCategory(category,PageRequest.of(offset, 6));
        Iterable<Product> products = page;

        if (offset >= page.getTotalPages()) {
            return "redirect:/menu/sets/page/"+ (page.getTotalPages() - 1);
        }

        model.addAttribute("page", page);

        model.addAttribute("products", products);

        model.addAttribute("url", "/menu/sets/page/");
        model.addAttribute("menu", "Меню сетов");

        return "menu";
    }

    @GetMapping("/menu/selection/{id}") // test
    public String menuSelection(@PathVariable(value = "id") int id, Model model) {

        if (!productRepository.existsById(id)) {
            return "redirect:/menu";
        }

        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        product.ifPresent(res::add);
        model.addAttribute("product", res);


        Category category = new Category();
        category.setId(res.get(0).getCategory().getId());

        Page<Product> page = productRepository.findByCategory(category,PageRequest.of(0, 4));
        Iterable<Product> products = page;
        model.addAttribute("products", products);

        return "menu-selection";
    }
}
