package com.example.Spotify.service.impl;

import com.example.Spotify.dto.ArtistDTO;
import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.repository.AlbumRepository;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.service.ArtistService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//import static com.example.Spotify.service.impl.AlbumServiceImpl.albumCoverLocation;


@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {
    private static final String albumCoverLocation = "src/main/java/com/example/Spotify/model/albums_cover/";

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public Artist addArtist(ArtistDTO artistDTO) {
        Artist artist = Artist.builder()
                .name(artistDTO.getName())
                .albums(new ArrayList<>())
                .songInfos(new ArrayList<>())
                .build();

        return artistRepository.save(artist);
    }

    @Override
    public List<Album> getAllAlbums(long artistId) {
        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        Artist artist = artistOpt.get();
        List<Album> albums = new ArrayList<>(artist.getAlbums());
        System.out.println("albums = " + albums);
        return albums;
    }



//    @Override
//    public List<Album> getAllAlbums(long id) {
//        Artist artist = artistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Artist Not Found"));
//        return artist.getAlbums();
//    }
}
