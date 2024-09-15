package com.example.Spotify.service;

import com.example.Spotify.dto.AlbumDTO;
import com.example.Spotify.dto.SongAlbumDTO;
import com.example.Spotify.dto.UpdateAlbumRequest;
import com.example.Spotify.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AlbumService {
    ResponseEntity<String> addAlbum(AlbumDTO albumDTO);
    Page<Album> getAlbumsSortedByPopularity(Pageable pageable);
    Album getAlbum(Long albumId);
    String deleteAlbum(Long artistId, String title);
    Album updateAlbum(UpdateAlbumRequest updateAlbumRequest);
    Album addSong(SongAlbumDTO songAlbumDTO);
    Album removeSong(SongAlbumDTO songAlbumDTO);
}
