package com.example.Spotify.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;



import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
@Entity

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private int likes;
    private String songURL;
    private String songCoverURL;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private PlayList playlist;
}
