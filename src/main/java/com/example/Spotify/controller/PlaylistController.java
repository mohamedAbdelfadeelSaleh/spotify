package com.example.Spotify.controller;

import com.example.Spotify.dto.UpdatePlaylistRequest;
import com.example.Spotify.dto.UpdateSongInPlaylistRequest;
import com.example.Spotify.dto.PlaylistRequest;
import com.example.Spotify.model.Playlist;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping()
    public ResponseEntity<String> addPlaylist(
            @RequestBody PlaylistRequest playlistRequest
    ) {
        return ResponseEntity.ok(playlistService.addPlaylist(playlistRequest));
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylistSortedByPopularity());
    }

    @GetMapping("/access")
    public ResponseEntity<List<SongInfo>> getPlaylists(
            @RequestBody UpdatePlaylistRequest updatePlaylistRequest
    ) {
        return ResponseEntity.ok(playlistService.getPlaylist(updatePlaylistRequest));
    }

    @DeleteMapping
    public ResponseEntity<String> removePlaylist(
            @RequestBody UpdatePlaylistRequest updatePlaylistRequest
    ) {
        return ResponseEntity.ok(playlistService.removePlaylist(updatePlaylistRequest));
    }

    @PutMapping("/changeVisibility")
    public ResponseEntity<String> changeVisibility(
            @RequestBody UpdatePlaylistRequest updatePlaylistRequest
    ){
        return ResponseEntity.ok(playlistService.changeVisibility(updatePlaylistRequest));
    }

    @PutMapping("/addSong")
    public ResponseEntity<String> addSong(
            @RequestBody UpdateSongInPlaylistRequest updateSongInPlaylistRequest
            ){
        return ResponseEntity.ok(playlistService.addSong(updateSongInPlaylistRequest));
    }

    @PutMapping("/removeSong")
    public ResponseEntity<String> removeSong(
            @RequestBody UpdateSongInPlaylistRequest updateSongInPlaylistRequest
    ){
        return ResponseEntity.ok(playlistService.removeSong(updateSongInPlaylistRequest));
    }

}
