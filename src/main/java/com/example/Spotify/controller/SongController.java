package com.example.Spotify.controller;

import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.service.AlbumService;
import com.example.Spotify.service.ArtistService;
import com.example.Spotify.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping("/search/{title}")
    public ResponseEntity<SearchResultDTO> search(@PathVariable String title) throws IOException {
        SongInfo songInfo = songService.findSongByTitle(title);
        System.out.println("search controller is called");
        if (songInfo == null) {
            throw new ResourceNotFoundException("No song with This title");
        }
        System.out.println(songInfo.getSongCoverURL());
        return ResponseEntity.ok(
                new SearchResultDTO().builder()
                        .song(songInfo)
                        .artist(songInfo.getArtist())
                        .songAlbum(songInfo.getAlbum())
                        .build()
        );
    }


    @PostMapping
    public ResponseEntity<SongInfo> uploadSongAndCover(
            @RequestParam String title,
            @RequestParam MultipartFile songFile,
            @RequestParam MultipartFile coverImageFile) throws IOException {

        songService.addSongAndCover(songFile, coverImageFile, title);
        SongInfo songInfo = songService.addSongInfo(title);

        return ResponseEntity.ok(songInfo);
    }

}
