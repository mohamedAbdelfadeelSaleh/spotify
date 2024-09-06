package com.example.Spotify.dto;

import com.example.Spotify.model.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAlbumRequest {
    private Long userId;
    private Long albumId;
    private String albumName;
    private Boolean isPremium;
    private MultipartFile albumCover;

}