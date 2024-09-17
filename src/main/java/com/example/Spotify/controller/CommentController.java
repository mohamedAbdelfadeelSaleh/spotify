package com.example.Spotify.controller;


import com.example.Spotify.model.Comment;
import com.example.Spotify.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/addComment/{songId}")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long songId,
            @RequestParam Long userId,
            @RequestBody String text) {
        return ResponseEntity.ok(commentService.addCommentToSong(userId, songId, text));
    }

    @PostMapping("/addReply/{parentCommentId}")
    public ResponseEntity<Comment> addReply(
            @PathVariable Long parentCommentId,
            @RequestBody Long userId,
            @RequestBody String text
    ){
        return ResponseEntity.ok(commentService.addReply(userId, parentCommentId, text));
    }

    @PutMapping("/UpdateComment/{commentId}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long commentId,
            @RequestBody Long userId,
            @RequestBody String newText
    ){
        return ResponseEntity.ok(commentService.updateComment(userId, commentId, newText));
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            @RequestBody Long useId
    ){
        return ResponseEntity.ok(commentService.deleteComment(commentId, useId));
    }
}
