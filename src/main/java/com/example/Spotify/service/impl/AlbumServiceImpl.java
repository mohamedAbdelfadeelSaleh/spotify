package com.example.Spotify.service.impl;


import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.repository.AlbumRepository;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.repository.SongRepository;
import com.example.Spotify.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private static final String albumCoverLocation = "src/main/java/com/example/Spotify/model/albums_cover/";

    @Override
    public String addAlbumCover(long artistId, String title, MultipartFile albumImageFile){
        System.out.println(artistId);
        System.out.println(title);
        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        Artist artist = artistOpt.orElse(null);
        System.out.println(artist);
        System.out.println(artist.getName());
        albumRepository.save(
                new Album().builder()
                        .name(title)
                        .isPremium(false)
                        .artist(artist)
                        .coverURL(albumCoverLocation + title + "jpg")
                        .releaseDate(new Date(System.currentTimeMillis()))
                        .build()
        );
        System.out.println("adding to database is done");
        File coverDir = new File(albumCoverLocation);
        if (!coverDir.exists())
            coverDir.mkdirs();
        System.out.println("creating file is done");
        try {
            byte[] imageBytes = albumImageFile.getBytes();
            FileOutputStream coverFileWriter = new FileOutputStream(albumCoverLocation + title + ".jpg");
            coverFileWriter.write(imageBytes);
            coverFileWriter.close();
        } catch (Exception e) {
            System.out.println("Image bad");
            ResponseEntity.badRequest();
        }
        return "album cover is uploaded ";
    }

}
