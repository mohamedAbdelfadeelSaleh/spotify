package com.example.Spotify.controller;
//
//
//import com.example.Spotify.service.AlbumService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/albums")
//public class AlbumController {
//    private final AlbumService albumService;
//
//    @Autowired
//    public AlbumController(AlbumService albumService) {
//        this.albumService = albumService;
//    }
//
//
//}


import com.example.Spotify.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @PostMapping("/{artistId}")
    public ResponseEntity<String> uploadAlbumAndCover(
            @PathVariable Long artistId,
            @RequestParam String title,
            @RequestParam MultipartFile albumImageFile) throws IOException {
        System.out.println("Controller is called");

        return ResponseEntity.ok( albumService.addAlbumCover(artistId, title, albumImageFile) );
    }

}
