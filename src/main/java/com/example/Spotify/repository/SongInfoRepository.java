package com.example.Spotify.repository;

import com.example.Spotify.dto.SongSearchDTO;
import com.example.Spotify.model.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SongInfoRepository extends JpaRepository<SongInfo, Long> {

    @Query(value = "SELECT new com.example.Spotify.dto.SongSearchDTO(s.id, s.title, s.artist.name, s.album.name, s.popularity) " +
            "FROM SongInfo s " +
            "WHERE s.title = :title")
    List<SongSearchDTO> findByTitle(@Param("title") String title);

}