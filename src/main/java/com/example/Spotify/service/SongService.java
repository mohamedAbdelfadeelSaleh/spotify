package com.example.Spotify.service;


import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.dto.SongDTO;
import com.example.Spotify.model.SongInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface SongService {
    SongInfo addSongInfo(String title);

    void addSongAndCover(MultipartFile songFile, MultipartFile coverImageFile, String name);

    SongInfo findSongByTitle(String title);

//    SongInfo like(Long id);
//    SongInfo dislike(long id);



    void addSongAndCover2(SongDTO songDTO, MultipartFile song, MultipartFile coverImageFile);

    SongInfo addSongInfo2(SongDTO songDTO);

}
