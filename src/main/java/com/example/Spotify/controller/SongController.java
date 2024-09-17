package com.example.Spotify.controller;

import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.dto.SongPlayDTO;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @PostMapping
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<SongInfo> uploadSongAndCover(
            @RequestParam String title,
            @RequestParam MultipartFile songFile,
            @RequestParam MultipartFile coverImageFile
    ){
        System.out.println("post song is called");
        songService.addSongAndCover(songFile, coverImageFile, title);
        SongInfo songInfo = songService.addSongInfo(title);
        return ResponseEntity.ok(songInfo);
    }

    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SearchResultDTO> search(
            @RequestParam String title
    ){
        System.out.println("search controller is called");
        return ResponseEntity.ok(songService.findSongByTitle(title));
    }

    @GetMapping("/stream")
    public ResponseEntity<SongPlayDTO> downloadFile(
            @RequestParam Long songId
    ) {
        return ResponseEntity.ok(songService.streamSong(songId));
    }

    @PutMapping("/like/{songId}/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SongInfo> like(
            @PathVariable Long songId,
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(songService.like(songId, userId));
    }

    @PutMapping("/dislike/{songId}/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SongInfo> dislike(
            @PathVariable Long songId,
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(songService.dislike(songId, userId));
    }

    @GetMapping("/allLiked/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<SongInfo>> getAllLikedSongs(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(songService.getUserLikedSongs(userId));
    }

    @GetMapping("/allDisliked/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<SongInfo>> getAllDislikedSongs(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(songService.getUserDislikedSongs(userId));
    }

    @DeleteMapping("/{songId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ARTIST')")
    public ResponseEntity<String> deleteSong(
            @PathVariable Long songId
    ) {
        return ResponseEntity.ok(songService.deleteSong(songId));
    }
}
