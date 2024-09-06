package com.example.Spotify.repository;

import com.example.Spotify.model.SongInfo;
import com.example.Spotify.model.SongPlaylistRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongPlaylistRelationRepository extends JpaRepository<SongPlaylistRelation, Long> {
    @Query( "SELECT spr.songInfo " +
            "FROM SongPlaylistRelation spr " +
            "WHERE spr.playlist.id = :playlistId")
    List<SongInfo> findByPlaylist(@Param("playlistId") Long playlistId);

    @Query( "SELECT spr " +
            "FROM SongPlaylistRelation spr " +
            "WHERE spr.playlist.id = :playlistId AND spr.songInfo.id = :songId")
    Optional<SongPlaylistRelation> findByPlaylistAndSong(@Param("playlistId")Long playlistId, @Param("songId") Long songId);

}
