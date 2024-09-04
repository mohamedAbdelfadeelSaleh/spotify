package com.example.Spotify.service;


import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.dto.SongDTO;
import com.example.Spotify.model.SongInfo;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.io.Resource;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface SongService {
    SongInfo addSongInfo(String title);

    void addSongAndCover(MultipartFile songFile, MultipartFile coverImageFile, String name);

    SearchResultDTO findSongByTitle(String title);

//    SongInfo like(long songId, long userId);
//    SongInfo dislike(long id, long userId);
    List<SongInfo> getUserLikedSongs(long userId);
    List<SongInfo> getUserDislikedSongs(long userId);

    void addSongAndCover2(SongDTO songDTO, MultipartFile song, MultipartFile coverImageFile);

    SongInfo addSongInfo2(SongDTO songDTO);

    MultipartFile getSongImage(String url) throws IOException;
    void updatePlayCount(long songId);

    //    @Transactional
    Resource play(long songId);

    String getFileName(Resource file);
}
