package com.example.Spotify.service;


import com.example.Spotify.model.Album;
import org.springframework.web.multipart.MultipartFile;

public interface AlbumService {

    Album getAlbum(long id);
    Album getByName(String name);
    //    String uplaodAlbumWithItsSongs(long artistId, AlbumDTO albumDTO);


    String addAlbumCover(long artistId, String title, MultipartFile albumImageFile);



    String addAlbum(String title);
    String addAlbum(String title, long artistId);
    String addSongToAlbum(long songId, long albumId);


}
