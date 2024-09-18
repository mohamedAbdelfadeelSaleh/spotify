package com.example.Spotify.service;


import com.example.Spotify.dto.*;
import com.example.Spotify.model.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {
    Comment addCommentToSong(AddCommentDTO AddCommentDTO);
    Page<Comment> getCommentsOnSong(GetCommentDTO getCommentDTO);
    CommentDTO addReply(AddReplyDTO AddReplyDTO);
    Comment updateComment(UpdateCommentDTO UpdateCommentDTO);
    String deleteComment(DeleteCommentDTO deleteComentDTO);
}
