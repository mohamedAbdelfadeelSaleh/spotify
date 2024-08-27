package com.example.Spotify.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SongDTO {
    private String title;

//    @JsonProperty("isPremium")
    private boolean isPremium;


}
