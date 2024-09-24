package com.example.Spotify.service.scheduledService;

import com.example.Spotify.model.Artist;
import com.example.Spotify.model.Subscription;
import com.example.Spotify.model.User;
import com.example.Spotify.model.UserSubscription;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.repository.UserRepository;
import com.example.Spotify.repository.UserSubscriptionRepository;
import com.example.Spotify.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final EmailService emailService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateSubscriptions() {
        Date today = new Date();

        userSubscriptionRepository.findAll().stream()
                .filter(UserSubscription::isActive)
                .filter(subscription -> subscription.getNextPaymentDate().compareTo(today) <= 0)
                .forEach(this::processSubscriptionRenewal);
    }

    private void processSubscriptionRenewal(UserSubscription subscription) {
        User user = subscription.getUser();
        Subscription subscriptionPlan = subscription.getSubscription();
        Artist artist = subscriptionPlan.getArtist();
        Double subscriptionPrice = subscriptionPlan.getPrice();
        if (user.getBalance() >= subscriptionPrice) {
            user.setBalance(user.getBalance() - subscriptionPrice);
            artist.setBalance(artist.getBalance() + subscriptionPrice);
            Calendar cal = Calendar.getInstance();
            cal.setTime(subscription.getNextPaymentDate());
            cal.add(Calendar.DATE, 30);
            subscription.setNextPaymentDate(cal.getTime());
            userRepository.save(user);
            artistRepository.save(artist);
            userSubscriptionRepository.save(subscription);
        } else {
            subscription.setActive(false);
            userSubscriptionRepository.save(subscription);
            emailService.sendSubscriptionFailureEmail(user, subscriptionPlan);
        }
    }
}
