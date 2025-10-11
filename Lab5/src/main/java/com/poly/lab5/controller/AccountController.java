package com.poly.lab5.controller;

import com.poly.lab5.service.CookieService;
import com.poly.lab5.service.ParamService;
import com.poly.lab5.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class AccountController {
    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }
    @PostMapping("/account/login")
    public String login2(Model model) {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);
        if("poly".equals(un) && "123".equals(pw)) {
            sessionService.set("username", un);
            if(rm==true) {
                cookieService.add("user", un, 10*24);
            } else {
                cookieService.remove("user");
            }
            model.addAttribute("message", "Đăng nhập thành công!");
        } else {
            model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
        }
        return "/account/login";
    }

    @GetMapping("/account/register")
    public String register1() {
        return "/account/register";
    }
    @PostMapping("/account/register")
    public String register2(Model model, @RequestParam("image") MultipartFile file) {
        try {
            String username = paramService.getString("username", "");
            String password = paramService.getString("password", "");

            File savedFile = paramService.save(file, "/uploads");

            if (savedFile != null&&!username.trim().isEmpty()&&!password.trim().isEmpty()) {
                model.addAttribute("message",
                        "Đăng ký thành công! Ảnh đã lưu tại: " + savedFile.getAbsolutePath());
            } else {
                model.addAttribute("message", "Không đủ thông tin!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }

        return "/account/register";
    }
}
