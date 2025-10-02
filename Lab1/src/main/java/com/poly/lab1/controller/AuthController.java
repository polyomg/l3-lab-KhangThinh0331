package com.poly.lab1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @Autowired
    HttpServletRequest request;
    @RequestMapping("/login/form")
    public String form() {
        return "form";
    }

    @RequestMapping("/login/check")
    public String login(Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if ("poly".equals(username) && "123".equals(password)) {
            return "index";
        } else {
            model.addAttribute("message", "Sai username hoặc password!");
            return "form";
        }

    }
}
