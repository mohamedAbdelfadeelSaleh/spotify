package com.example.Spotify.repository;

import com.example.Spotify.model.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SongInfoRepository extends JpaRepository<SongInfo, Long> {
    @Query(value = "SELECT * FROM song_info WHERE title = :title", nativeQuery = true)
    Optional<List<SongInfo>> findByTitle(@Param("title") String title);
}
