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
public class SongUploadDTO {
    private String title;
    private boolean isPremium;
    private MultipartFile songFile;
    private MultipartFile songCoverFile;
}
