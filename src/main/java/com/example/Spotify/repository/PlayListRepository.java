package com.example.Spotify.repository;

import com.example.Spotify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends JpaRepository<Playlist, Integer> {
}
