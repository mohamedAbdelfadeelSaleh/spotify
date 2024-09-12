
package com.example.Spotify.service;

import com.example.Spotify.dto.ArtistDTO;
import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;

import java.util.List;

public interface ArtistService {

    Artist addArtist(ArtistDTO artistDTO);
//    List<Album> getAllAlbums(long artistId);

}
