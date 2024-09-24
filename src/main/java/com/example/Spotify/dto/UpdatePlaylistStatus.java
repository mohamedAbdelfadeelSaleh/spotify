package com.example.Spotify.dto;

import com.example.Spotify.enums.PlaylistStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePlaylistStatus {
    private Long userId;
    private Long playlistId;
    private PlaylistStatus playlistStatus;
}
