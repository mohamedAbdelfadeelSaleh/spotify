package com.example.Spotify.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    UserDetailsService userDetailsService();

}
