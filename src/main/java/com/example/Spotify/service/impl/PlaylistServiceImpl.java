package com.example.Spotify.service.impl;

//import com.example.Spotify.dto.UpdateAlbumRequest;
import com.example.Spotify.dto.UpdatePlaylistRequest;
import com.example.Spotify.dto.UpdateSongInPlaylistRequest;
import com.example.Spotify.dto.PlaylistRequest;
import com.example.Spotify.model.Playlist;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.model.SongPlaylistRelation;
import com.example.Spotify.repository.PlaylistRepository;
import com.example.Spotify.repository.SongInfoRepository;
import com.example.Spotify.repository.SongPlaylistRelationRepository;
import com.example.Spotify.repository.UserRepository;
import com.example.Spotify.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongInfoRepository songInfoRepository;
    private final SongPlaylistRelationRepository songPlaylistRelationRepository;
    private final UserRepository userRepository;

    @Override
    public List<SongInfo> getPlaylist(
            UpdatePlaylistRequest updatePlaylistRequest
    ){
        Optional<Playlist> playlistOptional = playlistRepository.findById(updatePlaylistRequest.getPlaylistId());
        if(playlistOptional.isEmpty()){
            System.out.println("Playlist not found");
            return null;
        }
        Playlist playlist = playlistOptional.get();
        if(!playlist.getUser().equals(userRepository.findById(updatePlaylistRequest.getUserId()).get()) && playlist.isVisible()){
            System.out.println("this is private playlist");
            return null;
        }
        return songPlaylistRelationRepository.findByPlaylist(updatePlaylistRequest.getPlaylistId());
    }

    @Override
    public List<Playlist> getAllPlaylistSortedByPopularity(){
        return playlistRepository.getAllPlaylistSortedByPopularity();
    }

    @Override
    public String addPlaylist(
            PlaylistRequest playlistRequest
    ) {
        Optional<Playlist> playlistOpt = playlistRepository.findByUserAndPlaylist(playlistRequest.getUserId(), playlistRequest.getPlaylistName());
        if (playlistOpt.isPresent()) {
            return "playlist already exists";
        }
        Playlist playlist = Playlist.builder()
                .name(playlistRequest.getPlaylistName())
                .isVisible(playlistRequest.getIsVisible())
                .user(userRepository.findById(playlistRequest.getUserId()).get())
                .build();
        playlistRepository.save(playlist);
        return "playlist added";
    }

    @Override
    public String removePlaylist(
            UpdatePlaylistRequest updatePlaylistRequest
    ) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(updatePlaylistRequest.getPlaylistId());
        if (playlistOpt.isEmpty()) {
            return "playlist does not exist";
        }
        if (!playlistOpt.get().getUser().getId().equals(updatePlaylistRequest.getUserId())) {
            return "You can't remove this playlist";
        }
        playlistRepository.delete(playlistOpt.get());
        return "playlist is deleted successfully";
    }

    @Override
    public String changeVisibility(
            UpdatePlaylistRequest updatePlaylistRequest
    ) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(updatePlaylistRequest.getPlaylistId());
        if (playlistOpt.isEmpty()) {
            return "playlist does not exist";
        }
        Playlist playlist = playlistOpt.get();
        if (!playlist.getUser().getId().equals(updatePlaylistRequest.getUserId())) {
            return "You can't modify this playlist";
        }
        playlist.setVisible(!playlist.isVisible());
        playlistRepository.save(playlist);
        return "playlist visibility updated to " + playlist.isVisible();
    }

    @Override
    public String addSong(
            UpdateSongInPlaylistRequest updateSongInPlaylistRequest
    ) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(updateSongInPlaylistRequest.getPlaylistId());
        if (playlistOpt.isEmpty()) {
            return "playlist does not exist";
        }
        Playlist playlist = playlistOpt.get();
        if (!updateSongInPlaylistRequest.getUserId().equals(playlist.getUser().getId())) {
            return "You can't add this song to this playlist";
        }
        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(updateSongInPlaylistRequest.getSongId());
        if (songInfoOpt.isEmpty()) {
            return "song does not exist";
        }
        SongInfo songInfo = songInfoOpt.get();
        if(!songPlaylistRelationRepository.findByPlaylistAndSong(updateSongInPlaylistRequest.getPlaylistId(), updateSongInPlaylistRequest.getSongId()).isEmpty()) {
            return "song already exists";
        }
        SongPlaylistRelation songPlaylistRelation = SongPlaylistRelation.builder()
                .playlist(playlist)
                .songInfo(songInfo)
                .build();
        songPlaylistRelationRepository.save(songPlaylistRelation);
        return "song added to playlist";

    }

    @Override
    public String removeSong(
            UpdateSongInPlaylistRequest updateSongInPlaylistRequest
    ) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(updateSongInPlaylistRequest.getPlaylistId());
        if (playlistOpt.isEmpty()) {
            return "playlist does not exist";
        }
        Playlist playlist = playlistOpt.get();
        Optional<SongPlaylistRelation> relationOpt = songPlaylistRelationRepository.findByPlaylistAndSong(playlist.getId(), updateSongInPlaylistRequest.getSongId());
        if (relationOpt.isEmpty()) {
            return "song not found in the playlist";
        }
        songPlaylistRelationRepository.delete(relationOpt.get());
        return "song removed from playlist";
    }
}
