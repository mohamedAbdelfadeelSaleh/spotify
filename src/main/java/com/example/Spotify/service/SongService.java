package com.example.Spotify.service;


import com.example.Spotify.model.SongInfo;
import org.springframework.web.multipart.MultipartFile;


public interface SongService {
    SongInfo addSongInfo(String title);

    void addSongAndCover(MultipartFile songFile, MultipartFile coverImageFile, String name);

}
