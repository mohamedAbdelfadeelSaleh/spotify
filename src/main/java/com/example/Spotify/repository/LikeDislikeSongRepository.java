package com.example.Spotify.repository;

import com.example.Spotify.model.LikedDislikedSong;
import com.example.Spotify.model.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeDislikeSongRepository extends JpaRepository<LikedDislikedSong, Long> {

    @Query( "SELECT l " +
            "FROM LikedDislikedSong l " +
            "WHERE l.user.id = :userId AND l.songInfo.id = :songInfoId")
    Optional<LikedDislikedSong> findByUserAndSongInfo(@Param("userId") Long userId, @Param("songInfoId") Long songInfoId);

    @Query( "SELECT l.songInfo " +
            "FROM LikedDislikedSong l " +
            "WHERE l.user.id = :userId AND l.flag = true")
    List<SongInfo> findLikedSongsByUserId(@Param("userId") Long userId);

    @Query( "SELECT l.songInfo " +
            "FROM LikedDislikedSong l " +
            "WHERE l.user.id = :userId AND l.flag = false")
    List<SongInfo> findDislikedSongsByUserId(@Param("userId") Long userId);
}
