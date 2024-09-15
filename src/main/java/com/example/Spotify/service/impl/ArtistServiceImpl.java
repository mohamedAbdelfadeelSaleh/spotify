package com.example.Spotify.service.impl;

import com.example.Spotify.dto.ArtistDTO;
import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.service.ArtistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

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

}
