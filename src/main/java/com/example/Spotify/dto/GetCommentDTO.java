package com.example.Spotify.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCommentDTO {
    private Pageable pageable;
    private Long songId;
}
