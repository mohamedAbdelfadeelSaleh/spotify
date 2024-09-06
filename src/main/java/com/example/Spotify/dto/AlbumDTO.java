package com.example.Spotify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumDTO {
    private String name;
    private Long artistId;
    private MultipartFile albumCover;
    private Boolean isPremium;
}
