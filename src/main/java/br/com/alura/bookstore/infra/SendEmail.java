package br.com.alura.bookstore.infra;

public interface SendEmail {
    void sendEmail(String recipient, String subject, String message);
}
