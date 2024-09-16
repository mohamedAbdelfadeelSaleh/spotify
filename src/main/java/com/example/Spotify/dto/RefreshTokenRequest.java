package com.example.Spotify.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RefreshTokenRequest {
    private String token;

}
