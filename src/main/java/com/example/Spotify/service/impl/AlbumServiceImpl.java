package com.example.Spotify.service.impl;


import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.repository.AlbumRepository;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.repository.SongInfoRepository;
import com.example.Spotify.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final SongInfoRepository songInfoRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    private static final String albumCoverLocation = "src/main/java/com/example/Spotify/model/albums_cover/";


    private final static String UploadedSucessfully = "uploaded sucessfully";
    private final static String ArtistNotFound = "Artist not found";

    public Album getAlbum(long id){
        Optional<Album> albumOpt = albumRepository.findById(id);
        return albumOpt.orElse(null);
    }

    public Album getByName(String name){
        return albumRepository.findByName(name);
    }



    @Override
    public String addAlbum(String title){
        albumRepository.save(
                new Album().builder()
                        .name(title)
                        .isPremium(false)
//                        .artist(ar)
//                        .coverURL(albumCoverLocation + title + "jpg")
                        .releaseDate(new Date(System.currentTimeMillis()))
                        .songInfoInfos(null)
                        .build()
        );
        return UploadedSucessfully;
    }

    @Override
    public String addAlbum(String title, long artistId){
        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        Artist artist = artistOpt.orElse(null);
        albumRepository.save(
                new Album().builder()
                        .name(title)
                        .isPremium(false)
                        .artist(artist)
                        .coverURL(albumCoverLocation + title + "jpg")
                        .releaseDate(new Date(System.currentTimeMillis()))
                        .songInfoInfos(null)
                        .build()
        );
        return UploadedSucessfully;
    }


    @Override
    public ResponseEntity<String> addAlbumCover(long artistId, String title, MultipartFile albumImageFile) {
        System.out.println(artistId);
        System.out.println("title " + title);
        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        if (!artistOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist not found");
        }
        Artist artist = artistOpt.get();
        System.out.println(artist.getName());

        Album album = new Album().builder()
                .name(title)
                .isPremium(false)
                .artist(artist)
                .coverURL(albumCoverLocation + title + ".jpg")
                .releaseDate(new Date(System.currentTimeMillis()))
                .build();
        albumRepository.save(album);
        System.out.println("adding to database is done");

        File coverDir = new File(albumCoverLocation);
        if (!coverDir.exists()) {
            coverDir.mkdirs();
        }
        System.out.println("creating file is done");

        try {
            byte[] imageBytes = albumImageFile.getBytes();
            FileOutputStream coverFileWriter = new FileOutputStream(albumCoverLocation + title + ".jpg");
            coverFileWriter.write(imageBytes);
            coverFileWriter.close();
        } catch (Exception e) {
            System.out.println("Image bad");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to upload album cover");
        }

        return ResponseEntity.ok("Album cover is uploaded");
    }


    @Override
    public String addSongToAlbum(long songId, long albumId){
        Optional<SongInfo> songOpt = songInfoRepository.findById(songId);
        if(songOpt.isEmpty()){
            return "Song Not Found";
        }
        SongInfo songInfo = songOpt.get();
        Optional<Album> albumOpt = albumRepository.findById(albumId);
        if(albumOpt.isEmpty()){
            return "album Not Found";
        }
        Album album = albumOpt.get();
        songInfo.setAlbum(album);
        album.getSongInfoInfos().add(songInfo);
        songInfoRepository.save(songInfo);
        albumRepository.save(album);
        return songInfo.getTitle() + " is added to " + album.getName();
    }

    @Override
    public ResponseEntity<String> deleteAlbum(long artistId, String title){

        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        if (!artistOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist not found");
        }
        Optional<Album> albumOpt = albumRepository.findById(artistId);
        if (albumOpt.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album not found");
        }
        Album album = albumOpt.get();
        List<SongInfo> songInfos = album.getSongInfoInfos();
        for (SongInfo songInfo : songInfos) {
            songInfoRepository.delete(songInfo);
        }
        albumRepository.delete(album);

        return ResponseEntity.ok().body("deleted successfully");
    }


//    @Transactional
//    public String uplaodAlbumWithItsSongs(
//            long artistId,
//            AlbumDTO albumDTO
//    ){
//        Optional<Artist> artistOpt = artistRepository.findById(artistId);
//
//        if(artistOpt.isEmpty()){
//            return ArtistNotFound;
//        }
//        Artist artist = artistOpt.get();
//
//        if (albumDTO.getSongs() == null || albumDTO.getSongs().isEmpty()) {
//            return "An album must contain at least one song";
//        }
//
//
//        String albumCoverURL = fileStorageService.storeFile(albumDTO.getAlbumCover());
//
//        Album album = Album.builder()
//                .name(albumDTO.getAlbumName())
//                .coverURL(albumCoverURL)
//                .releaseDate(new Data(System.currentTimeMillis()))
//                .isPremium(albumDTO.isPremium())
//                .artist(artist)
//                .build();
//
//        albumRepository.save(album);
//
//        for (SongUploadDTO songDTO : albumDTO.getSongs()) {
//            // Save song file and song cover image
//            String songURL = fileStorageService.storeFile(songDTO.getSongFile());
//            String songCoverURL = fileStorageService.storeFile(songDTO.getSongCoverFile());
//
//            SongInfo song = SongInfo.builder()
//                    .title(songDTO.getTitle())
//                    .songURL(songURL)
//                    .songCoverURL(songCoverURL)
//                    .publishDate(songDTO.getPublishDate())
//                    .isPremium(songDTO.isPremium())
//                    .artist(artist)
//                    .album(album)
//                    .build();
//
//            songInfoRepository.save(song);
//        }
//
//
//        return UploadedSucessfully;
//    }

}
