package com.example.Spotify.repository;

import com.example.Spotify.dto.GetCommentDTO;
import com.example.Spotify.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query( "SELECT c " +
            "FROM Comment c " +
            "where c.songInfo.id =:songId")
    Page<Comment> findCommentsOnSong(@Param("songId") Long songId, Pageable pageable);
}
