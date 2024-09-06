package com.example.Spotify.service;

import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.dto.SongPlayDTO;
import com.example.Spotify.model.SongInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


public interface SongService {
    void addSongAndCover(MultipartFile songFile, MultipartFile coverImageFile, String name);
    SongInfo addSongInfo(String title);
    SongPlayDTO streamSong(Long songId);
    String getSongNameById(Long songId);
    Resource loadFileAsResource(String title);
    SearchResultDTO findSongByTitle(String title);
    SongInfo like(long songId, long userId);
    SongInfo dislike(long id, long userId);
    List<SongInfo> getUserLikedSongs(long userId);
    List<SongInfo> getUserDislikedSongs(long userId);

}
