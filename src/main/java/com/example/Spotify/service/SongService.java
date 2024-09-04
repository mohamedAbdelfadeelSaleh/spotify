package com.example.Spotify.service;

import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.model.SongInfo;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


public interface SongService {
    SongInfo addSongInfo(String title);
    void addSongAndCover(MultipartFile songFile, MultipartFile coverImageFile, String name);
    SearchResultDTO findSongByTitle(String title);
    SongInfo like(long songId, long userId);
    SongInfo dislike(long id, long userId);
    List<SongInfo> getUserLikedSongs(long userId);
    List<SongInfo> getUserDislikedSongs(long userId);

}
