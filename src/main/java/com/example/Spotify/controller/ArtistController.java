package com.example.Spotify.controller;

import com.example.Spotify.model.Album;
import com.example.Spotify.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/{artistId}")
    public ResponseEntity<List<Album>> getAlbumsByArtist(
            @PathVariable Long artistId
    ){
        return ResponseEntity.ok(artistService.getAllAlbums(artistId));
    }
}
