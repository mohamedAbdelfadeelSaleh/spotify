package com.example.Spotify.service;

//import com.example.Spotify.dto.UpdateAlbumRequest;
import com.example.Spotify.dto.UpdatePlaylistRequest;
import com.example.Spotify.dto.UpdateSongInPlaylistRequest;
import com.example.Spotify.dto.PlaylistRequest;
import com.example.Spotify.model.Playlist;
import com.example.Spotify.model.SongInfo;

import java.util.List;

public interface PlaylistService {
    List<SongInfo> getPlaylist(UpdatePlaylistRequest updatePlaylistRequest);
    List<Playlist> getAllPlaylistSortedByPopularity();
    String addPlaylist(PlaylistRequest playlistRequest);
    String removePlaylist(UpdatePlaylistRequest updatePlaylistRequest);
    String changeVisibility(UpdatePlaylistRequest updatePlaylistRequest);
    String addSong(UpdateSongInPlaylistRequest updateSongInPlaylistRequest);
    String removeSong(UpdateSongInPlaylistRequest updateSongInPlaylistRequest);

}
