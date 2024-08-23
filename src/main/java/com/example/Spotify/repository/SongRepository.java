package com.example.Spotify.repository;

import com.example.Spotify.model.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<SongInfo, Long> {

}
