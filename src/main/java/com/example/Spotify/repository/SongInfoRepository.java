package com.example.Spotify.repository;

import com.example.Spotify.dto.SongSearchDTO;
import com.example.Spotify.model.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SongInfoRepository extends JpaRepository<SongInfo, Long> {

    @Query(value = "SELECT (s.id, s.title, s.artist.name, s.album.name, s.playCount) " +
            "FROM SongInfo s " +
            "WHERE s.title = :title")
    Optional<List<SongSearchDTO>> findByTitle(@Param("title") String title);
}
