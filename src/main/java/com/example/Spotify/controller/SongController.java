package com.example.Spotify.controller;


import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.dto.SongDTO;
import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.service.AlbumService;
import com.example.Spotify.service.ArtistService;
import com.example.Spotify.service.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

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



    @PostMapping
    public ResponseEntity<SongInfo> uploadSongAndCover(
            @RequestParam String title,
            @RequestParam MultipartFile songFile,
            @RequestParam MultipartFile coverImageFile) throws IOException {

        songService.addSongAndCover(songFile, coverImageFile, title);
        SongInfo songInfo = songService.addSongInfo(title);

        return ResponseEntity.ok(songInfo);
    }



//    @PutMapping("/like/{songId}")
//    public ResponseEntity<SongInfo> like(@PathVariable Long songId){
//        return ResponseEntity.ok(songService.like(songId));
//    }
//
//    @PutMapping("/dislike/{songId}")
//    public ResponseEntity<SongInfo> dislike(@PathVariable Long songId){
//        return ResponseEntity.ok(songService.dislike(songId));
//    }

//    @GetMapping("/play/{songId}")
//    public ResponseEntity<MultipartFile> play(@PathVariable Long songId){
//
////                return ResponseEntity.ok(songService.like(songId));
//    }








//    @PostMapping()
//    public ResponseEntity<SongInfo> uploadSongAndCover2(
//            @RequestParam SongDTO songDTO,
//            @RequestParam MultipartFile songFile,
//            @RequestParam MultipartFile coverImageFile) throws IOException {
//        System.out.println("Controller is called");
//
//        try {
//            songService.addSongAndCover2(songDTO, songFile, coverImageFile);
//            SongInfo songInfo = songService.addSongInfo2(songDTO);
//            return ResponseEntity.ok(songInfo);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
//
//





//

//    @PostMapping
//    public ResponseEntity<SongInfo> uploadSongAndCover2(
//            @RequestParam("json") String songDTO,
//            @RequestParam("songFile") MultipartFile songFile,
//            @RequestParam("coverImageFile") MultipartFile coverImageFile) throws IOException {
//        System.out.println("Controller1 is called");
//        String jsonString = "{\"title\": \"asas\"}";
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            SongDTO hamdy = objectMapper.readValue(jsonString, SongDTO.class);
//            System.out.println(hamdy);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            System.out.println(songDTO);
//
//            SongDTO songDTO1 = objectMapper.readValue(songDTO, SongDTO.class);
//            System.out.println(songDTO1);
//            System.out.println("mapper is working");
//            songService.addSongAndCover2(songDTO1, songFile, coverImageFile);
//            SongInfo songInfo = songService.addSongInfo2(songDTO1);
//            return ResponseEntity.ok(songInfo);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }




}
