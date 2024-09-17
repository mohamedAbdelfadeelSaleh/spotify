package com.example.Spotify.controller;

import com.example.Spotify.dto.AlbumDTO;
import com.example.Spotify.dto.SongAlbumDTO;
import com.example.Spotify.dto.UpdateAlbumRequest;
import com.example.Spotify.model.Album;
import com.example.Spotify.repository.AlbumRepository;
import com.example.Spotify.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/albums")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<String> addAlbum(
            @RequestParam("name") String name,
            @RequestParam("artistId") Long artistId,
            @RequestParam("isPremium") Boolean isPremium,
            @RequestParam("albumCover") MultipartFile albumCover
    ) {
        return albumService.addAlbum(AlbumDTO.builder()
                .name(name)
                .artistId(artistId)
                .isPremium(isPremium)
                .albumCover(albumCover)
                .build());
    }

    @GetMapping
    public ResponseEntity<Page<Album>> getAllAlbums(Pageable pageable) {
        return ResponseEntity.ok(albumService.getAlbumsSortedByPopularity(pageable));
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<Album> getAlbumById(
            @PathVariable Long albumId
    ) {
        return ResponseEntity.ok(albumService.getAlbum(albumId));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAlbum(
            @RequestParam("artistId") Long artistId,
            @RequestParam("name") String name) {
        return ResponseEntity.ok(albumService.deleteAlbum(artistId, name));
    }

    @PutMapping
    public ResponseEntity<Album> updateAlbum(
            @RequestParam Long userId,
            @RequestParam Long albumId,
            @RequestParam String albumName,
            @RequestParam Boolean isPremium,
            @RequestParam MultipartFile albumCover
        ){
        return ResponseEntity.ok(albumService.updateAlbum(UpdateAlbumRequest.builder()
                .userId(userId)
                .albumId(albumId)
                .albumName(albumName)
                .isPremium(isPremium)
                .albumCover(albumCover)
                .build())
        );
    }

    @PutMapping("/addSong")
    public ResponseEntity<Album> addSong(
            @RequestBody SongAlbumDTO songAlbumDTO
    ){
        return ResponseEntity.ok(albumService.addSong(songAlbumDTO));
    }

    @PutMapping("/removeSong")
    public ResponseEntity<Album> removeSong(
            @RequestBody SongAlbumDTO songAlbumDTO
    ){
        return ResponseEntity.ok(albumService.removeSong(songAlbumDTO));
    }
}
