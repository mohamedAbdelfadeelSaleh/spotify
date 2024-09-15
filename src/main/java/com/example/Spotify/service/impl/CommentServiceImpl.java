package com.example.Spotify.service.impl;

import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.Comment;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.model.User;
import com.example.Spotify.repository.CommentRepository;
import com.example.Spotify.repository.SongInfoRepository;
import com.example.Spotify.repository.UserRepository;
import com.example.Spotify.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final SongInfoRepository songInfoRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public Comment addCommentToSong(long userId, long songId, String text) {
        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(songId);
        if (songInfoOpt.isEmpty()) {
            System.out.println("Song Not Found");
            throw new ResourceNotFoundException("Song Not Found");
        }
        SongInfo songInfo = songInfoOpt.get();
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            System.out.println("User Not Found");
            throw new ResourceNotFoundException("User Not Found");
        }
        User user = userOpt.get();
        Comment comment = new Comment();
        comment.setText(text);
        comment.setSongInfo(songInfo);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Override
    public Comment addReply(long userId, long parentCommentId, String text) {
        Optional<Comment> parentCommentOpt = commentRepository.findById(parentCommentId);
        if (parentCommentOpt.isEmpty()) {
            System.out.println("Parent Comment Not Found");
            throw new ResourceNotFoundException("Parent Comment Not Found");
        }
        Comment parentComment = parentCommentOpt.get();
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            System.out.println("User Not Found");
            throw new ResourceNotFoundException("User Not Found");
        }
        User user = userOpt.get();
        Comment reply = new Comment();
        reply.setText(text);
        reply.setParentComment(parentComment);
        reply.setSongInfo(parentComment.getSongInfo());  // Reply is related to the same song
        reply.setUser(user);
        return commentRepository.save(reply);
    }
    @Override
    public Comment updateComment(long userId, long commentId, String text){
        Optional<Comment> commentOpt = commentRepository.findById(commentId);
        if (commentOpt.isEmpty()) {
            System.out.println("Comment Not Found");
            throw new ResourceNotFoundException("Comment Not Found");
        }
        Comment comment = commentOpt.get();
        comment.setText(text);
        return commentRepository.save(comment);
    }

    @Override
    public String deleteComment(long userId, long commentId){
        Optional<Comment> commentOpt = commentRepository.findById(commentId);
        if (commentOpt.isEmpty()) {
            System.out.println("Comment Not Found");
            throw new ResourceNotFoundException("Comment Not Found");
        }
        Comment comment = commentOpt.get();
        commentRepository.delete(comment);
        return "Comment Deleted";
    }

}