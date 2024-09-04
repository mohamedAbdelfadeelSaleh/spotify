package com.example.Spotify.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AlbumService {
    ResponseEntity<String> addAlbumCover(long artistId, String title, MultipartFile albumImageFile);
    ResponseEntity<String> deleteAlbum(long artistId, String title);

}
