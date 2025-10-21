package com.poly.lab8.service;

import lombok.Builder;
import lombok.Data;

public interface MailService {

@Data
@Builder
public static class Mail {
    @Builder.Default
    private String from = "WebShop <thinhlktc00224@fpt.edu.vn>";
    private String to, cc, bcc, subject, body, filenames;
}

    void send(Mail mail);

    default void send(String to, String subject, String body) {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }

    void push(Mail mail);

    default void push(String to, String subject, String body){
        this.push(Mail.builder().to(to).subject(subject).body(body).build());
    }
}
