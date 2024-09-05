package com.example.Spotify.service;

import org.springframework.web.multipart.MultipartFile;

public interface AlbumService {
    String addAlbumCover(long artistId, String title, MultipartFile albumImageFile);
}
