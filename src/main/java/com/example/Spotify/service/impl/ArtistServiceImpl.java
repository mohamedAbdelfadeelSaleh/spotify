package com.example.Spotify.service.impl;

import com.example.Spotify.dto.ArtistDTO;
import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.service.ArtistService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
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

}
