package com.example.Spotify.service;


import com.example.Spotify.model.Comment;

public interface CommentService {
    Comment addCommentToSong(long userId, long songId, String text);
    Comment addReply(long userId, long parentCommentId, String text);
    Comment updateComment(long userId, long commentId, String text);
    String deleteComment(long userId, long commentId);
}
