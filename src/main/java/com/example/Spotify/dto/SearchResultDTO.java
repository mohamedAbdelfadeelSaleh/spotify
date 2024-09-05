package com.example.Spotify.dto;

import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.model.SongInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchResultDTO {
    private SongInfo song;
    private MultipartFile image;
    private Artist artist;
    private Album songAlbum;

}
