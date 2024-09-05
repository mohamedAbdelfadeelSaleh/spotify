package com.example.Spotify.controller;


import com.example.Spotify.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/albums")
@RequiredArgsConstructor()
public class AlbumController {
    private final AlbumService albumService;


}
