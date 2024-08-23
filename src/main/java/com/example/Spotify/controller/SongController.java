package com.example.Spotify.controller;


import com.example.Spotify.model.Song;
import com.example.Spotify.service.SongService;
import com.example.Spotify.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        Song savedSong = songService.addSong(song);
        return ResponseEntity.ok(savedSong);
    }
}
