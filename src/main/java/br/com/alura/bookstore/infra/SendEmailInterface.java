package br.com.alura.bookstore.infra;

import org.springframework.scheduling.annotation.Async;

public interface SendEmailInterface {
    @Async
    void sendEmail(String recipient, String subject, String message);
}
