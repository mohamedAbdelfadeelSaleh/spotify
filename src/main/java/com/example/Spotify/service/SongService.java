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




    void addSongAndCover2(SongDTO songDTO, MultipartFile song, MultipartFile coverImageFile);

    SongInfo addSongInfo2(SongDTO songDTO);


//    void addLike(long songId);
//
//    void removeLike(long songId);
//
//    void addDislike(long songId);
//
//    void removeDislike(long songId);
}
