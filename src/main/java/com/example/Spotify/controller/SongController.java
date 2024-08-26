package com.example.Spotify.controller;


import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.dto.SongDTO;
import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.service.AlbumService;
import com.example.Spotify.service.ArtistService;
import com.example.Spotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @Autowired
    public SongController(SongService songService, ArtistService artistService, AlbumService albumService) {
        this.songService = songService;
        this.artistService = artistService;
        this.albumService = albumService;
    }

//
    @PostMapping
    public ResponseEntity<SongInfo> uploadSongAndCover(
            @RequestParam String title,
            @RequestParam MultipartFile songFile,
            @RequestParam MultipartFile coverImageFile) throws IOException {

        songService.addSongAndCover(songFile, coverImageFile, title);
        SongInfo songInfo = songService.addSongInfo(title);

        return ResponseEntity.ok(songInfo);
    }


//    @PostMapping
//    public ResponseEntity<SongInfo> uploadSongAndCover2(
//            @RequestParam SongDTO songDTO,
//            @RequestParam MultipartFile songFile,
//            @RequestParam MultipartFile coverImageFile) throws IOException {
//        System.out.println("Controller is called");
////        songService.addSongAndCover2(songDTO, songFile, coverImageFile);
////
////        SongInfo songInfo = songService.addSongInfo2(songDTO);
////
////        return ResponseEntity.ok(songInfo);
//
//        try {
//            songService.addSongAndCover2(songDTO, songFile, coverImageFile);
//            SongInfo songInfo = songService.addSongInfo2(songDTO);
//            return ResponseEntity.ok(songInfo);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }




    @GetMapping("/search/{title}")
    public ResponseEntity<SearchResultDTO> search(@PathVariable String title) {


        SongInfo songInfo = songService.findSongByTitle(title);
        System.out.println("search controller is called");
        if (songInfo == null) {
            throw new ResourceNotFoundException("No song with This title");
        }
        return ResponseEntity.ok(
                new SearchResultDTO().builder()
                        .song(songInfo)
                        .artist(songInfo.getArtist())
                        .songAlbum(songInfo.getAlbum())
                        .build()
        );
    }

}
