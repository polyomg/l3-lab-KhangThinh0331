package com.poly.lab8.controller;

import com.poly.lab8.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class MailController {
    @Autowired
    MailService mailService;
    @ResponseBody
    @RequestMapping("/mail/send")
    public String send() {
        try {
            mailService.push("receiver@gmail.com", "Subject", "Body");
            return "Mail của bạn đã được xếp vào hàng đợi";
        } catch (Exception e) {
            return "Lỗi gửi mail: " + e.getMessage();
        }
    }
    @GetMapping("/mail/form")
    public String form() {
        return "/mail";
    }

    @PostMapping("/mail/sending")
    public String send(@RequestParam String from,
                       @RequestParam String to,
                       @RequestParam(required = false) String cc,
                       @RequestParam(required = false) String bcc,
                       @RequestParam String subject,
                       @RequestParam String body,
                       @RequestParam("files") MultipartFile[] files,
                       Model model) throws IOException {

        String filenames = saveAttachments(files);
        MailService.Mail mail = MailService.Mail.builder()
                .from(from)
                .to(to)
                .cc(cc)
                .bcc(bcc)
                .subject(subject)
                .body(body)
                .filenames(filenames)
                .build();

        mailService.send(mail);
        model.addAttribute("message", "Mail đã được gửi ngay lập tức!");
        return "/mail";
    }

    @PostMapping("/mail/push")
    public String push(@RequestParam String from,
                       @RequestParam String to,
                       @RequestParam(required = false) String cc,
                       @RequestParam(required = false) String bcc,
                       @RequestParam String subject,
                       @RequestParam String body,
                       @RequestParam("files") MultipartFile[] files,
                       Model model) throws IOException {

        String filenames = saveAttachments(files);
        MailService.Mail mail = MailService.Mail.builder()
                .from(from)
                .to(to)
                .cc(cc)
                .bcc(bcc)
                .subject(subject)
                .body(body)
                .filenames(filenames)
                .build();

        mailService.push(mail);
        model.addAttribute("message", "📨 Mail đã được xếp vào hàng đợi!");
        return "/mail";
    }

    private String saveAttachments(MultipartFile[] files) throws IOException {
        StringBuilder filenames = new StringBuilder();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                File saveFile = new File(System.getProperty("java.io.tmpdir"), file.getOriginalFilename());
                file.transferTo(saveFile);
                filenames.append(saveFile.getAbsolutePath()).append(";");
            }
        }
        return filenames.toString();
    }
}
