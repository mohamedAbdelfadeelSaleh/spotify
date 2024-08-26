package com.example.Spotify.repository;

import com.example.Spotify.model.Artist;
import com.example.Spotify.model.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<SongInfo> findByName(String name);

}
