package br.com.alura.bookstore.infra;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "test"})
public class SendEmailFake implements SendEmailInterface {

    @Async
    public void sendEmail(String recipient, String subject, String message) {
        System.out.println("Enviando email: ");
        System.out.println("Destinatario: " + recipient);
        System.out.println("Assunto: " + subject);
        System.out.println("Mensagem " + message);
    }
}
