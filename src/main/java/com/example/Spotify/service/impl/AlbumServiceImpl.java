package com.example.Spotify.service.impl;


import  com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.repository.AlbumRepository;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.repository.SongRepository;
import com.example.Spotify.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlbumServiceImpl implements AlbumService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    private static final String albumCoverLocation = "src/main/java/com/example/Spotify/model/albums_cover/";
    private final static String UploadedSucessfully = "uploaded sucessfully";

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
    public String addAlbumCover(long artistId, String title, MultipartFile albumImageFile){
        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        Artist artist = artistOpt.orElse(null);
        albumRepository.save(
                new Album().builder()
                        .name(title)
                        .isPremium(false)
                        .artist(artist)
                        .coverURL(albumCoverLocation + title + "jpg")
                        .releaseDate(new Date(System.currentTimeMillis()))
                        .build()
        );
        File coverDir = new File(albumCoverLocation);
        if (!coverDir.exists())
            coverDir.mkdirs();
        try {
            byte[] imageBytes = albumImageFile.getBytes();
            FileOutputStream coverFileWriter = new FileOutputStream(albumCoverLocation + title + ".jpg");
            coverFileWriter.write(imageBytes);
            coverFileWriter.close();
        } catch (Exception e) {
            ResponseEntity.badRequest();
        }
        return "album cover is uploaded ";
    }


    @Override
    public String addSongToAlbum(long songId, long albumId){
        Optional<SongInfo> songOpt = songRepository.findById(songId);
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
        songRepository.save(songInfo);
        albumRepository.save(album);
        return songInfo.getTitle() + " is added to " + album.getName();
    }
}
