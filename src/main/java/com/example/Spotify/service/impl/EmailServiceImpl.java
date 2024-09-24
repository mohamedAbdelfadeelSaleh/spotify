package com.example.Spotify.service.impl;

import com.example.Spotify.dto.SendEmailDTO;
import com.example.Spotify.model.Subscription;
import com.example.Spotify.model.User;
import com.example.Spotify.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    @Override
    public void sendEmail(SendEmailDTO sendEmailDTO) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(sendEmailDTO.getTo());
        mailMessage.setSubject(sendEmailDTO.getSubject());
        mailMessage.setText(sendEmailDTO.getBody());
        mailSender.send(mailMessage);
    }

    @Override
    public void sendSubscriptionFailureEmail(User user, Subscription subscription) {
        String toAddress = user.getEmail();
        String subject = "Subscription Payment Failed";
        String body = String.format(
                "Dear %s, \n\nYour balance is insufficient to renew your subscription to the %s tier for the artist %s. " +
                        "Please top up your balance to continue enjoying the benefits.\n\nThank you!",
                user.getFirstName(), subscription.getName(), subscription.getArtist().getName()
        );

        sendEmail(SendEmailDTO.builder()
                .to(toAddress)
                .subject(subject)
                .body(body)
                .build());
    }
}
