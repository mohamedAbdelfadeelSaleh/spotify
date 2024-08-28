package com.example.Spotify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SongSearchResult {
    private long id;
    private String title;
    private int likes;
    private int playcount;
    private boolean premium;

}
