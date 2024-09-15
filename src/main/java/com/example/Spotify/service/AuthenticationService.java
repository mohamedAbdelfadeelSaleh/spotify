package com.example.Spotify.service;

import com.example.Spotify.dto.JwtAuthenticationResponse;
import com.example.Spotify.dto.SignUpRequest;
import com.example.Spotify.dto.SigninRequest;


public interface AuthenticationService {
    void signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
}
