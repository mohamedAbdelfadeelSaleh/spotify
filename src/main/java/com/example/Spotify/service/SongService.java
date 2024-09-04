package com.example.Spotify.service;


import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.dto.SongDTO;
import com.example.Spotify.model.SongInfo;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public interface SongService {
    SongInfo addSongInfo(String title);

    void addSongAndCover(MultipartFile songFile, MultipartFile coverImageFile, String name);

    SongInfo findSongByTitle(String title);

//    SongInfo like(Long id);
//    SongInfo dislike(long id);



    void addSongAndCover2(SongDTO songDTO, MultipartFile song, MultipartFile coverImageFile);

    SongInfo addSongInfo2(SongDTO songDTO);

    MultipartFile getSongImage(String url) throws IOException;

    Resource play(long songId);

    @Transactional
    Resource play(Long songId);

    void updatePlayCount(long songId);

    String getFileName(Resource file);
}
