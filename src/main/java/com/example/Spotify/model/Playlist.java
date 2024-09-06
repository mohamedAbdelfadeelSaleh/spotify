package com.example.Spotify.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private long id;
    private String name;
    private boolean isVisible;
    private int popularity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LikedDislikedPlaylist> likedPlaylists = new ArrayList<>();

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SongPlaylistRelation> songPlaylistRelation = new ArrayList<>();
}
