
package com.example.Spotify.service;

import com.example.Spotify.dto.ArtistDTO;
import com.example.Spotify.model.Artist;

public interface ArtistService {
    Artist addArtist(ArtistDTO artistDTO);
}
