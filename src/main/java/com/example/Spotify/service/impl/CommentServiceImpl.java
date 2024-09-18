package com.example.Spotify.service.impl;

import com.example.Spotify.dto.*;
import com.example.Spotify.enums.Role;
import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.Comment;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.model.User;
import com.example.Spotify.repository.CommentRepository;
import com.example.Spotify.repository.SongInfoRepository;
import com.example.Spotify.repository.UserRepository;
import com.example.Spotify.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final SongInfoRepository songInfoRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public Comment addCommentToSong(AddCommentDTO addCommentDTO) {
        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(addCommentDTO.getSongId());
        if (songInfoOpt.isEmpty()) {
            System.out.println("Song Not Found");
            throw new ResourceNotFoundException("Song Not Found");
        }
        SongInfo songInfo = songInfoOpt.get();
        Optional<User> userOpt = userRepository.findById(addCommentDTO.getUserId());
        if (userOpt.isEmpty()) {
            System.out.println("User Not Found");
            throw new ResourceNotFoundException("User Not Found");
        }
        User user = userOpt.get();
        Comment comment = new Comment();
        comment.setText(addCommentDTO.getText());
        comment.setSongInfo(songInfo);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Override
    public Page<Comment> getCommentsOnSong(
            GetCommentDTO getCommentDTO
    ) {
        return commentRepository.findCommentsOnSong(getCommentDTO.getSongId(), getCommentDTO.getPageable());
    }

    @Override
    public CommentDTO addReply(AddReplyDTO addReplyDTO) {
        System.out.println("service is called");
        Optional<Comment> parentCommentOpt = commentRepository.findById(addReplyDTO.getParentCommentId());
        if (parentCommentOpt.isEmpty()) {
            System.out.println("Parent Comment Not Found");
            throw new ResourceNotFoundException("Parent Comment Not Found");
        }
        Comment parentComment = parentCommentOpt.get();
        System.out.println(parentComment.getText());
        Optional<User> userOpt = userRepository.findById(addReplyDTO.getUserId());
        if (userOpt.isEmpty()) {
            System.out.println("User Not Found");
            throw new ResourceNotFoundException("User Not Found");
        }
        User user = userOpt.get();
        Comment reply = Comment.builder()
                .text(addReplyDTO.getReply())
                .parentComment(parentComment)
                .songInfo(parentComment.getSongInfo())
                .user(user)
                .build();

        commentRepository.save(reply);
        return CommentDTO.builder()
                .id(reply.getId())
                .text(reply.getText())
                .parentCommentText(parentComment.getText())
                .songInfo(reply.getSongInfo())
                .user(reply.getUser())
                .build();
    }

    @Override
    public Comment updateComment(UpdateCommentDTO updateCommentDTO){
        Optional<Comment> commentOpt = commentRepository.findById(updateCommentDTO.getCommentId());
        if (commentOpt.isEmpty()) {
            System.out.println("Comment Not Found");
            throw new ResourceNotFoundException("Comment Not Found");
        }
        Comment comment = commentOpt.get();
        User user = userRepository.findById(updateCommentDTO.getUserId()).get();
        if (!user.getRole().equals(Role.ADMIN) && updateCommentDTO.getUserId() != comment.getUser().getId()) {
            System.out.println("You are not authorized to update this comment");
        }
        comment.setText(updateCommentDTO.getComment());
        return commentRepository.save(comment);
    }

    @Override
    public String deleteComment(DeleteCommentDTO deleteCommentDTO){
        Optional<Comment> commentOpt = commentRepository.findById(deleteCommentDTO.getCommentId());
        if (commentOpt.isEmpty()) {
            System.out.println("Comment Not Found");
            throw new ResourceNotFoundException("Comment Not Found");
        }
        Comment comment = commentOpt.get();
        User user = userRepository.findById(deleteCommentDTO.getUserId()).get();
        if (!user.getRole().equals(Role.ADMIN) &&  (!comment.getUser().getId().equals(deleteCommentDTO.getUserId()))) {
            System.out.println("You are not authorize to delete this comment");
            throw new RuntimeException("you are not authorize to delete this comment");
        }
        commentRepository.delete(comment);
        return "Comment Deleted";
    }

}