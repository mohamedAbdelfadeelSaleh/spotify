
package com.example.Spotify.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private SongInfo songInfo;
}
