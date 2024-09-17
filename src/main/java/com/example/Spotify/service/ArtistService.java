
package com.example.Spotify.service;

import com.example.Spotify.dto.ArtistDTO;
import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistService {
    List<Album> getAllAlbums(long artistId);
}
