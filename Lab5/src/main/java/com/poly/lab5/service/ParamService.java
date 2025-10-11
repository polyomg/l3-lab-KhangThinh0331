package com.poly.lab5.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {

    @Autowired
    HttpServletRequest request;

    /**
     * Đọc chuỗi giá trị của tham số
     */
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null && !value.trim().isEmpty()) ? value.trim() : defaultValue;
    }

    /**
     * Đọc số nguyên giá trị của tham số
     */
    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        try {
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Đọc số thực giá trị của tham số
     */
    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        try {
            return (value != null) ? Double.parseDouble(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Đọc giá trị boolean của tham số
     */
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value == null) return defaultValue;
        return Boolean.parseBoolean(value);
    }

    /**
     * Đọc giá trị thời gian của tham số
     */
    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value == null || value.isEmpty()) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Định dạng ngày không hợp lệ cho tham số: " + name);
        }
    }

    /**
     * Lưu file upload vào thư mục
     */
    public File save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            // Lấy đường dẫn tuyệt đối của thư mục lưu file
            String realPath = System.getProperty("user.dir") + path;
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Tạo file mới với tên gốc
            File savedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;

        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage());
        }
    }
}

