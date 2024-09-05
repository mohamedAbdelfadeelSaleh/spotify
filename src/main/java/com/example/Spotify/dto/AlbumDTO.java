package com.example.Spotify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumDTO {
    private String albumName;
    private boolean isPremium;
    private MultipartFile albumCover;
    private List<SongUploadDTO> songs;
}
