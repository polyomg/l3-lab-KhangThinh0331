package com.poly.lab2.controller;

import com.poly.lab2.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"item","itemss","items"})
public class ProductController {
    @GetMapping("/product/form")
    public String form(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Product p = new Product();
        p.setName("iPhone 30");
        p.setPrice(5000.0);
        model.addAttribute("item", p);
        if (!model.containsAttribute("itemss")) {
            model.addAttribute("itemss", new Product());
        }
        int pageSize = 5;
        int fromIndex = page * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, list.size());

        List<Product> subList = list.subList(fromIndex, toIndex);

        model.addAttribute("items", subList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int)Math.ceil((double)list.size() / pageSize));
        return "product";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute("itemss") Product p, RedirectAttributes ra) {
        list.add(new Product(p.getName(), p.getPrice()));
        ra.addFlashAttribute("itemss", p);
        return "redirect:/product/form";
    }

    private List<Product> list = new ArrayList<>(Arrays.asList(
            new Product("Oppo30", 1000.0),
            new Product("Oppo100", 12000.0),
            new Product("Oppo20", 13000.0),
            new Product("Oppo25", 24000.0),
            new Product("Oppo35", 12000.0),
            new Product("Iphone 7", 50000.0),
            new Product("Iphone 8", 7000.0),
            new Product("Iphone 9", 8000.0),
            new Product("Iphone 10", 11000.0),
            new Product("Iphone 11", 13000.0),
            new Product("Samsung 1", 90000.0),
            new Product("Samsung 4", 100000.0),
            new Product("Samsung 6", 29000.0),
            new Product("Samsung 10", 27000.0),
            new Product("Samsung 15", 63000.0),
            new Product("Xiaomi 55", 78000.0),
            new Product("Xiaomi 89", 82000.0),
            new Product("Xiaomi 32", 46000.0),
            new Product("Xiaomi 18", 74000.0),
            new Product("Xiaomi 22", 66000.0)
    ));
}
