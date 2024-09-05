package com.example.Spotify.controller;

import com.example.Spotify.dto.JwtAuthenticationResponse;
import com.example.Spotify.dto.RefreshTokenRequest;
import com.example.Spotify.dto.SignUpRequest;
import com.example.Spotify.dto.SigninRequest;
import com.example.Spotify.model.User;
import com.example.Spotify.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @RequestBody SignUpRequest signUpRequest
    ){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(
            @RequestBody SigninRequest signinRequest
    ){

        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }


    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(
            @RequestBody RefreshTokenRequest refreshTokenRequest
    ){

        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }



}
