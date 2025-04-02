package com.example.Spotify.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private long id;
    private String name;
    private int popularity;
    private String imageURL;
    private Double balance;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SongInfo> songInfos = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Album> albums = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subscription> subscriptionList = new ArrayList();

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<UserArtistFollow> userArtistFollowList = new ArrayList();
}
