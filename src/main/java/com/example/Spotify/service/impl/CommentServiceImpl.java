package com.example.Spotify.service.impl;

import com.example.Spotify.model.Comment;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.repository.CommentRepository;
import com.example.Spotify.repository.SongInfoRepository;
import com.example.Spotify.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private SongInfoRepository songInfoRepository;
    private CommentRepository commentRepository;


    @Override
    public String addComment(long songId, String text) {
        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(songId);
        if (songInfoOpt.isEmpty()) {
            return "Song Not Found";
        }
        SongInfo songInfo = songInfoOpt.get();

        Comment comment = new Comment();
        comment.setText(text);
        comment.setSongInfo(songInfo);
        songInfo.getComments().add(comment);
        commentRepository.save(comment);
        songInfoRepository.save(songInfo);
        return "Comment Added";

    }

}