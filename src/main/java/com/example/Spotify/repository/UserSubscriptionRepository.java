package com.example.Spotify.repository;

import com.example.Spotify.model.UserSubscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {
    List<UserSubscription> findAll();
}
