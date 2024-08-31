package com.example.Spotify.service.impl;

import com.example.Spotify.dto.JwtAuthenticationResponse;
import com.example.Spotify.dto.SignUpRequest;
import com.example.Spotify.dto.SigninRequest;
import com.example.Spotify.model.User;
import com.example.Spotify.repository.UserRepository;
import com.example.Spotify.service.AuthenticationService;
import com.example.Spotify.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;




@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        System.out.println("sign in service is called ");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword())
        );

        User user = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new JwtAuthenticationResponse(jwt, refreshToken);
//        return new JwtAuthenticationResponse();
    }

    @Override
    public void signup(SignUpRequest signUpRequest) {
        if (!isValidEmail(signUpRequest.getEmail())) {
            System.out.println("invalid Email");
            throw new IllegalArgumentException("Invalid email");
        }
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            System.out.println("email is already in use");
            throw new IllegalArgumentException("Email is already in use");
        }
        if (!isValidPassword(signUpRequest.getPassword())) {
            System.out.println("Use strong password");
            throw new IllegalArgumentException("Password must be at least 8 characters long, contain letters and numbers");
        }
        userRepository.save(User.builder()
                .email(signUpRequest.getEmail())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(signUpRequest.getRole())
                .build()
        );
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8
                && password.matches(".*\\d.*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[A-Z].*")
                && password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
