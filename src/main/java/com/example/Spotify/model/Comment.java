
package com.example.Spotify.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private SongInfo songInfo;
}
