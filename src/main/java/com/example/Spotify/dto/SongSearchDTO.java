package com.example.Spotify.dto;

import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongSearchDTO {
    private Long id;
    private String title;
    private String artist;
    private String album;
    private int popularity;
}
