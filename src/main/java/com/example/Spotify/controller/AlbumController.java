package com.example.Spotify.controller;


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




//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadAlbumAndCoverAndArtistId(
//            @RequestParam String title,
//            @RequestParam MultipartFile albumImageFile) throws IOException {
//
//        String albumRes = albumService.addAlbum(title, artistId);
//        String coverRes = albumService.addAlbumCover(title, albumImageFile);
//
////        if(res.equals("Artist Not Found"))
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
//        return ResponseEntity.ok(albumRes + coverRes );
//    }
//
//    @PostMapping("/addSong/{songid}/album/{albumId}")
//    public ResponseEntity<String> addSongToAlbum(@PathVariable long songId, @PathVariable long albumId){
//        String res = albumService.addSongToAlbum(songId, albumId);
//        return ResponseEntity.ok(res);
//    }
}
