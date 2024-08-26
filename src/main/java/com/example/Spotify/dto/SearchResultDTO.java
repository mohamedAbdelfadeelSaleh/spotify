package com.example.Spotify.dto;

import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.model.SongInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchResultDTO {
    private SongInfo song;
    private Artist artist;
    private Album songAlbum;
//    private Album artistNewestAlbum;

}
