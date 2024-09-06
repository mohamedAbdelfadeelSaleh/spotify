package com.example.Spotify.repository;

import com.example.Spotify.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query( "SELECT a " +
            "From Album a " +
            "LEFT JOIN a.songInfo s " +
            "GROUP BY a " +
            "ORDER BY " +
            "SUM (2 * s.likes + s.playCount - s.dislikes) DESC")
    Page<Album> getAlbumsSortedByPopularity(Pageable pageable);
}
