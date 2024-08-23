package com.example.Spotify.service.impl;

import com.example.Spotify.model.Song;
import com.example.Spotify.repository.SongRepository;
import com.example.Spotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;


    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song addSong(Song song) {
        return songRepository.save(song);
    }
}
