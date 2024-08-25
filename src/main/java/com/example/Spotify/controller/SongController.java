package com.example.Spotify.controller;


import com.example.Spotify.model.SongInfo;
import com.example.Spotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }


    @PostMapping
    public ResponseEntity<SongInfo> uploadSongAndCover(
            @RequestParam String title,
            @RequestParam MultipartFile songFile,
            @RequestParam MultipartFile coverImageFile) throws IOException {

        System.out.println("this controller is called");
        songService.addSongAndCover(songFile, coverImageFile, title);
        SongInfo songInfo = songService.addSongInfo(title);

        return ResponseEntity.ok(songInfo);
    }
}
