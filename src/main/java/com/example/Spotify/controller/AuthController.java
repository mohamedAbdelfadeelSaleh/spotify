package com.example.Spotify.controller;

import com.example.Spotify.dto.JwtAuthenticationResponse;
import com.example.Spotify.dto.SignUpRequest;
import com.example.Spotify.dto.SigninRequest;
import com.example.Spotify.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        authenticationService.signup(signUpRequest);
        return ResponseEntity.ok("Signed in succsessfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest) {
        System.out.println("signin controller is called");
        JwtAuthenticationResponse response = authenticationService.signin(signinRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/login/success")
    public ResponseEntity<String> loginSuccess() {
        return ResponseEntity.ok("OAuth2 login successful!");
    }
}
