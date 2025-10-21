package com.poly.lab8.controller;

import com.poly.lab8.entity.Account;
import com.poly.lab8.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    AccountService accountService;
    @Autowired
    HttpSession session;
    @GetMapping("/auth/login")
    public String loginForm(Model model) {
        return "/auth/login";
    }
    @PostMapping("/auth/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {
        Account user = accountService.findById(username);
        if(user == null) {
            model.addAttribute("message", "Invalid username!");
        } else if(!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
        } else{
            session.setAttribute("user", user);
            model.addAttribute("message", "Login successfully!");
            String securityUri = (String)session.getAttribute("securityUri");
            if(securityUri != null) {
                return "redirect:" + securityUri;
            }
        }
        return "/auth/login";
    }
    @GetMapping("/account/edit-profile")
    public String eidtProfile() {
        return "/auth/edit-profile";
    }
    @GetMapping("/account/change-password")
    public String changePassword() {
        return "/auth/change-password";
    }
    @GetMapping("/order/list")
    public String order() {
        return "/auth/order";
    }
    @GetMapping("/admin/list")
    public String admin() {
        return "/auth/admin";
    }
    @GetMapping("/admin/home/index")
    public String home() {
        return "/auth/home";
    }
}
