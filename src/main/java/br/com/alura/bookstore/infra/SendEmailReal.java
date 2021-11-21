package br.com.alura.bookstore.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class SendEmailReal implements SendEmail {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void sendEmail(String recipient, String subject, String message){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipient);
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }
}
