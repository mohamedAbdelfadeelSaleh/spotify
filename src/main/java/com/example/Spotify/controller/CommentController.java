package com.example.Spotify.controller;


import com.example.Spotify.dto.*;
import com.example.Spotify.model.Comment;
import com.example.Spotify.service.CommentService;
import com.example.Spotify.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;
    private final JWTService jwtService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> addComment(
            @RequestBody AddCommentDTO addCommentDTO,
            @RequestHeader("Authorization") String token
    ) {
        Long userId = jwtService.extractUserId(token.substring(7));
        System.out.println(userId);
        addCommentDTO.setUserId(userId);
        return ResponseEntity.ok(
                commentService.addCommentToSong(addCommentDTO)
        );
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<Comment>> getComments(
            @RequestParam(value = "songId") Long songId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(
                commentService.getCommentsOnSong(
                        GetCommentDTO.builder()
                                .songId(songId)
                                .pageable(PageRequest.of(page, size))
                                .build()
                )
        );
    }

    @PostMapping("/reply")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentDTO> addReply(
            @RequestBody AddReplyDTO addReplyDTO,
            @RequestHeader("Authorization") String token
    ){
        Long userId = jwtService.extractUserId(token.substring(7));
        addReplyDTO.setUserId(userId);
        System.out.println("controller is called");
        return ResponseEntity.ok(
                commentService.addReply(addReplyDTO)
        );
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> updateComment(
            @RequestBody UpdateCommentDTO updateCommentDTO,
            @RequestHeader("Authorization") String token
            ){
        Long userId = jwtService.extractUserId(token.substring(7));
        updateCommentDTO.setUserId(userId);
        return ResponseEntity.ok(
                commentService.updateComment(updateCommentDTO)
        );
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteComment(
            @RequestBody DeleteCommentDTO deleteCommentDTO,
            @RequestHeader("Authorization") String token
    ){
        Long userId = jwtService.extractUserId(token.substring(7));
        deleteCommentDTO.setUserId(userId);
        return ResponseEntity.ok(
                commentService.deleteComment(deleteCommentDTO)
        );
    }
}
