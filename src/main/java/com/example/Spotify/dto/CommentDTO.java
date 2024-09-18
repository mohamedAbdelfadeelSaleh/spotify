package com.example.Spotify.dto;

import com.example.Spotify.model.SongInfo;
import com.example.Spotify.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String text;
    private String parentCommentText;
    private SongInfo songInfo;
    private User user;
}
