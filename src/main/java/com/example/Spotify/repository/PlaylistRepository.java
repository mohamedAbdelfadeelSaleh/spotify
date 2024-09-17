package com.example.Spotify.repository;

import com.example.Spotify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    @Query( "SELECT p " +
            "FROM Playlist p " +
            "WHERE p.user.id = :userId AND p.name = :playlistName")
    Optional<Playlist> findByUserAndPlaylist(@Param("userId") Long userId, @Param("playlistName") String playlistName);

    @Query( "SELECT p " +
            "FROM Playlist p " +
            "WHERE p.id = :playlistId")
    Optional<Playlist> findById(@Param("playlistId") Long playlistId);

    @Query( "SELECT p " +
            "FROM Playlist p " +
            "ORDER BY p.popularity DESC ")
    List<Playlist> getAllPlaylistSortedByPopularity();
}
