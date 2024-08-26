package com.example.Spotify.dto;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SongDTO {
    private String title;
    private boolean isPremium;
//    private Date publishDate;

}
