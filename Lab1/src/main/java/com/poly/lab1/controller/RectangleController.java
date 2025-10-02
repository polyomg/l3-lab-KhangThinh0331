package com.poly.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RectangleController {
    @Autowired
    HttpServletRequest request;
    @RequestMapping("/rectangle/form")
    public String form() {
        return "rectangle";
    }

    @RequestMapping("/rectangle/calc")
    public String calculate(Model model) {
        String widthStr = request.getParameter("width");
        String lengthStr = request.getParameter("length");

        try {
            Double width = Double.parseDouble(widthStr);
            Double length = Double.parseDouble(lengthStr);

            if (width <= 0 || length <= 0) {
                model.addAttribute("message", "Chiều dài và chiều rộng phải lớn hơn 0.");
            } else if(width > length) {
                    Double a = width;
                    width = length;
                    length = a;
                double area = width * length;
                double perimeter = 2 * (width + length);

                model.addAttribute("area", length + " x " + width + " = " + area);
                model.addAttribute("perimeter", "2 x (" + length + " + " + width + ") = " + perimeter);
            }
            else {
                double area = width * length;
                double perimeter = 2 * (width + length);

                model.addAttribute("area", length + " x " + width + " = " + area);
                model.addAttribute("perimeter", "2 x (" + length + " + " + width + ") = " + perimeter);
            }
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Vui lòng nhập số hợp lệ cho chiều dài và chiều rộng.");
        }
        return "rectangle";
    }
}
