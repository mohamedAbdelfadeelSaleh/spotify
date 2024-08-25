package com.example.Spotify.model;

import jakarta.persistence.OneToMany;



import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SongInfo> songInfos = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Album> albums = new ArrayList<>();

}
