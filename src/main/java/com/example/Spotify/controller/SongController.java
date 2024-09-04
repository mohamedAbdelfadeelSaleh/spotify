package com.example.Spotify.controller;


import com.example.Spotify.dto.SearchResultDTO;

import com.example.Spotify.dto.SongDTO;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.service.SongService;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;


    @GetMapping("/search/{title}")
    public ResponseEntity<SearchResultDTO> search(
            @PathVariable String title
    ) throws IOException {

        System.out.println("search controller is called");
        return ResponseEntity.ok(songService.findSongByTitle(title));
    }

    @PostMapping
    public ResponseEntity<SongInfo> uploadSongAndCover(
            @RequestParam String title,
            @RequestParam MultipartFile songFile,
            @RequestParam MultipartFile coverImageFile) throws IOException {
        System.out.println("upload song controller is called");
        songService.addSongAndCover(songFile, coverImageFile, title);
        SongInfo songInfo = songService.addSongInfo(title);

        return ResponseEntity.ok(songInfo);
    }



//    @PutMapping("/like/{songId}/{userId}")
//    public ResponseEntity<SongInfo> like(
//            @PathVariable Long songId,
//            @PathVariable Long userId
//    ){
//        return ResponseEntity.ok(songService.like(songId, userId));
//    }
//
//    @PutMapping("/dislike/{songId}/{userId}")
//    public ResponseEntity<SongInfo> dislike(
//            @PathVariable Long songId,
//            @PathVariable Long userId
//    ){
//        return ResponseEntity.ok(songService.dislike(songId, userId));
//    }

    @GetMapping("/allLiked/{userId}")
    public ResponseEntity<List<SongInfo>> getAllLikedSongs(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(songService.getUserLikedSongs(userId));
    }

    @GetMapping("/allDisliked/{userId}")
    public ResponseEntity<List<SongInfo>> getAllDislikedSongs(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(songService.getUserDislikedSongs(userId));
    }





//    @GetMapping("/{songId}/play")
//    public ResponseEntity<Resource> playSong(@PathVariable Long songId){
//        Resource song = songService.play(songId);
//        songService.updatePlayCount(songId);
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(song);
//
//    }



    @GetMapping("/play/{id}")
    public ResponseEntity<Resource> playSong(@PathVariable Long id) {
        Resource file = songService.play(id);
        String fileName = songService.getFileName(file);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file);
    }
//






//
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
