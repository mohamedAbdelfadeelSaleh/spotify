package com.example.Spotify.controller;
//
//import com.example.Spotify.dto.ArtistDTO;
//import com.example.Spotify.model.Artist;
//import com.example.Spotify.service.ArtistService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//
//@RestController
//@CrossOrigin()
//@RequestMapping("/api/v1/artists")
//public class ArtistController {
//
//    private final ArtistService artistService;
//
//    @Autowired
//    public ArtistController(ArtistService artistService) {
//        this.artistService = artistService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Artist> addArtist(@RequestBody ArtistDTO artistDTO) {
//        System.out.println("Controller called");
//        Artist artist = artistService.addArtist(artistDTO);
//        return ResponseEntity.ok(artist);
//    }
//
////    @GetMapping
////    public ResponseEntity<List<Artist>> getAllArtists() {
////        List<ArtistDTO> artistDTOs=  artistService.getAllArtistDTOs();
////    }
//
//
//
//}



import com.example.Spotify.dto.ArtistDTO;
import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api/v1/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    //this may need to be moved to another controller after security
    @PostMapping
    public ResponseEntity<Artist> addArtist(@RequestBody ArtistDTO artistDTO) {
        System.out.println("Artist Controller called");
        Artist artist = artistService.addArtist(artistDTO);
        return ResponseEntity.ok(artist);
    }

//
//    @GetMapping("/{artistId}")
//    public ResponseEntity<List<Album>> getAlbumsByArtist(@PathVariable long id){
//        return ResponseEntity.ok(artistService.getAllAlbums(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Artist>> getAllArtists() {
//        List<ArtistDTO> artistDTOs=  artistService.getAllArtistDTOs();
//    }



}
