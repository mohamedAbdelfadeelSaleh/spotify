package com.example.Spotify.service;

import com.example.Spotify.dto.SendEmailDTO;
import com.example.Spotify.model.Subscription;
import com.example.Spotify.model.User;

public interface EmailService {
    void sendEmail(SendEmailDTO sendEmailDTO);
    void sendSubscriptionFailureEmail(User user, Subscription subscription);
}
