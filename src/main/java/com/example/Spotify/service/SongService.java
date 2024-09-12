package com.example.Spotify.service;

import com.example.Spotify.model.SongInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface SongService {
    SongInfo addSongInfo(String title);
    void addSongAndCover(MultipartFile songFile, MultipartFile coverImageFile, String name);
    SongInfo findSongByTitle(String title);
    MultipartFile getSongImage(String url) throws IOException;
}
