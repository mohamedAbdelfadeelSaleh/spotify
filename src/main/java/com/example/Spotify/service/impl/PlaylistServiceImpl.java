package com.example.Spotify.service.impl;

//import com.example.Spotify.dto.UpdateAlbumRequest;
import com.example.Spotify.dto.UpdatePlaylistRequest;
import com.example.Spotify.dto.UpdatePlaylistStatus;
import com.example.Spotify.dto.UpdateSongInPlaylistRequest;
import com.example.Spotify.dto.PlaylistRequest;
import com.example.Spotify.enums.PlaylistStatus;
import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.*;
import com.example.Spotify.repository.PlaylistRepository;
import com.example.Spotify.repository.SongInfoRepository;
import com.example.Spotify.repository.SongPlaylistRelationRepository;
import com.example.Spotify.repository.UserRepository;
import com.example.Spotify.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//
//public class PlaylistServiceImpl implements PlaylistService {
//
//    private final PlaylistRepository playlistRepository;
//    private final SongInfoRepository songInfoRepository;
//    private final SongPlaylistRelationRepository songPlaylistRelationRepository;
//    private final UserRepository userRepository;
//
//    @Override
//    public List<SongInfo> getPlaylist(
//            UpdatePlaylistRequest updatePlaylistRequest
//    ){
//        Optional<Playlist> playlistOptional = playlistRepository.findById(updatePlaylistRequest.getPlaylistId());
//        if(playlistOptional.isEmpty()){
//            System.out.println("Playlist not found");
//            return null;
//        }
//        Playlist playlist = playlistOptional.get();
//        if(!playlist.getUser().equals(userRepository.findById(updatePlaylistRequest.getUserId()).get()) && playlist.isVisible()){
//            System.out.println("this is private playlist");
//            return null;
//        }
//        return songPlaylistRelationRepository.findByPlaylist(updatePlaylistRequest.getPlaylistId());
//    }
//
//    @Override
//    public List<Playlist> getAllPlaylistSortedByPopularity(){
//        return playlistRepository.getAllPlaylistSortedByPopularity();
//    }
//
//    @Override
//    public String addPlaylist(
//            PlaylistRequest playlistRequest
//    ) {
//        Optional<Playlist> playlistOpt = playlistRepository.findByUserAndPlaylist(playlistRequest.getUserId(), playlistRequest.getPlaylistName());
//        if (playlistOpt.isPresent()) {
//            return "playlist already exists";
//        }
//        Playlist playlist = Playlist.builder()
//                .name(playlistRequest.getPlaylistName())
//                .isVisible(playlistRequest.getIsVisible())
//                .user(userRepository.findById(playlistRequest.getUserId()).get())
//                .build();
//        playlistRepository.save(playlist);
//        return "playlist added";
//    }
//
//    @Override
//    public String removePlaylist(
//            UpdatePlaylistRequest updatePlaylistRequest
//    ) {
//        Optional<Playlist> playlistOpt = playlistRepository.findById(updatePlaylistRequest.getPlaylistId());
//        if (playlistOpt.isEmpty()) {
//            return "playlist does not exist";
//        }
//        if (!playlistOpt.get().getUser().getId().equals(updatePlaylistRequest.getUserId())) {
//            return "You can't remove this playlist";
//        }
//        playlistRepository.delete(playlistOpt.get());
//        return "playlist is deleted successfully";
//    }
//
//    @Override
//    public String changeVisibility(
//            UpdatePlaylistRequest updatePlaylistRequest
//    ) {
//        Optional<Playlist> playlistOpt = playlistRepository.findById(updatePlaylistRequest.getPlaylistId());
//        if (playlistOpt.isEmpty()) {
//            return "playlist does not exist";
//        }
//        Playlist playlist = playlistOpt.get();
//        if (!playlist.getUser().getId().equals(updatePlaylistRequest.getUserId())) {
//            return "You can't modify this playlist";
//        }
//        playlist.setVisible(!playlist.isVisible());
//        playlistRepository.save(playlist);
//        return "playlist visibility updated to " + playlist.isVisible();
//    }
//
//    @Override
//    public String addSong(
//            UpdateSongInPlaylistRequest updateSongInPlaylistRequest
//    ) {
//        if (canUpadatePlaylist(new UpdatePlaylistRequest(updateSongInPlaylistRequest.getUserId(), updateSongInPlaylistRequest.getPlaylistId()))) {
//            return "could not add song";
//        }
//        Optional<Playlist> playlistOpt = playlistRepository.findById(updateSongInPlaylistRequest.getPlaylistId());
//        if (playlistOpt.isEmpty()) {
//            return "playlist does not exist";
//        }
//        Playlist playlist = playlistOpt.get();
//        if (!updateSongInPlaylistRequest.getUserId().equals(playlist.getUser().getId())) {
//            return "You can't add this song to this playlist";
//        }
//        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(updateSongInPlaylistRequest.getSongId());
//        if (songInfoOpt.isEmpty()) {
//            return "song does not exist";
//        }
//        SongInfo songInfo = songInfoOpt.get();
//        if(!songPlaylistRelationRepository.findByPlaylistAndSong(updateSongInPlaylistRequest.getPlaylistId(), updateSongInPlaylistRequest.getSongId()).isEmpty()) {
//            return "song already exists";
//        }
//        SongPlaylistRelation songPlaylistRelation = SongPlaylistRelation.builder()
//                .playlist(playlist)
//                .songInfo(songInfo)
//                .build();
//        songPlaylistRelationRepository.save(songPlaylistRelation);
//        return "song added to playlist";
//
//    }
//
//    @Override
//    public String removeSong(
//            UpdateSongInPlaylistRequest updateSongInPlaylistRequest
//    ) {
//        if (!canUpadatePlaylist(new UpdatePlaylistRequest(updateSongInPlaylistRequest.getPlaylistId(), updateSongInPlaylistRequest.getPlaylistId()))) {
//            return "Could not update playlist";
//        }
////        Optional<Playlist> playlistOpt = playlistRepository.findById(updateSongInPlaylistRequest.getPlaylistId());
////        if (playlistOpt.isEmpty()) {
////            return "playlist does not exist";
////        }
////        Playlist playlist = playlistOpt.get();
//        Optional<SongPlaylistRelation> relationOpt = songPlaylistRelationRepository.findByPlaylistAndSong(updateSongInPlaylistRequest.getPlaylistId(), updateSongInPlaylistRequest.getSongId());
//        if (relationOpt.isEmpty()) {
//            return "song not found in the playlist";
//        }
//        songPlaylistRelationRepository.delete(relationOpt.get());
//        return "song removed from playlist";
//    }
//
//    @Override
//    public String updateStatus(UpdatePlaylistStatus updatePlaylistStatus){
//        Optional<Playlist> playlistOpt = playlistRepository.findById(updatePlaylistStatus.getPlaylistId());
//        if (playlistOpt.isEmpty()) {
//            System.out.println("Playlist not found");
//            throw new ResourceNotFoundException("Playlist not found");
//        }
//        Playlist playlist = playlistOpt.get();
//        Optional<User> userOpt = userRepository.findById(updatePlaylistStatus.getUserId());
//        if (userOpt.isEmpty()) {
//            System.out.println("User not found");
//            throw new ResourceNotFoundException("User not found");
//        }
//        User user = userOpt.get();
//        if (!Objects.equals(user.getId(), playlist.getUser().getId())) {
//            System.out.println("Not authotize to modify this");
//            throw new ResourceNotFoundException("Not authenticated");
//        }
//        playlist.setStatus(updatePlaylistStatus.getPlaylistStatus());
//        playlistRepository.save(playlist);
//        return "playlist status updated to " + updatePlaylistStatus.getPlaylistStatus();
//    }
//
//    private boolean canUpadatePlaylist(
//            UpdatePlaylistRequest updatePlaylistRequest
//    ) {
//        Optional<Playlist> playlistOpt = playlistRepository.findById(updatePlaylistRequest.getPlaylistId());
//        if (playlistOpt.isEmpty()) {
//            System.out.println("Playlist not found");
//            return false;
//        }
//        Playlist playlist = playlistOpt.get();
//        Optional<User> userOpt = userRepository.findById(updatePlaylistRequest.getUserId());
//        if (userOpt.isEmpty()) {
//            System.out.println("User not found");
//            return false;
//        }
//        User user = userOpt.get();
//        List<Long> contributorsIdList = playlist.getPlaylistContributorsList().stream()
//                .map(PlaylistContributors::getId)
//                .toList();
//        if (
//                Objects.equals(user.getId(), playlist.getUser().getId())
//                        || (
//                        playlist.getStatus()!=PlaylistStatus.PRIVATE
//                        && contributorsIdList.contains(updatePlaylistRequest.getUserId())
//                )
//        ) {
//            return true;
//        }
//        System.out.println("Not authotize to modify this");
//        return false;
//    }
//}

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongInfoRepository songInfoRepository;
    private final SongPlaylistRelationRepository songPlaylistRelationRepository;
    private final UserRepository userRepository;

    @Override
    public List<SongInfo> getPlaylist(UpdatePlaylistRequest updatePlaylistRequest) {
        Playlist playlist = findPlaylistById(updatePlaylistRequest.getPlaylistId());
        User user = findUserById(updatePlaylistRequest.getUserId());

        if (!canAccessPlaylist(user, playlist)) {
            throw new ResourceNotFoundException("You don't have permission to access this playlist.");
        }

        return songPlaylistRelationRepository.findByPlaylist(updatePlaylistRequest.getPlaylistId());
    }

    @Override
    public List<Playlist> getAllPlaylistSortedByPopularity() {
        return playlistRepository.getAllPlaylistSortedByPopularity();
    }

    @Override
    public String addPlaylist(PlaylistRequest playlistRequest) {
        User user = findUserById(playlistRequest.getUserId());

        boolean playlistExists = playlistRepository.findByUserAndPlaylist(user.getId(), playlistRequest.getPlaylistName()).isPresent();
        if (playlistExists) {
            return "Playlist already exists";
        }

        Playlist playlist = Playlist.builder()
                .name(playlistRequest.getPlaylistName())
                .status(playlistRequest.getPlaylistStatus())
                .user(user)
                .build();

        playlistRepository.save(playlist);
        return "Playlist added";
    }

    @Override
    public String removePlaylist(UpdatePlaylistRequest updatePlaylistRequest) {
        Playlist playlist = findPlaylistById(updatePlaylistRequest.getPlaylistId());
        User user = findUserById(updatePlaylistRequest.getUserId());

        if (!playlist.getUser().equals(user)) {
            throw new ResourceNotFoundException("You can't remove this playlist");
        }

        playlistRepository.delete(playlist);
        return "Playlist is deleted successfully";
    }

    @Override
    public String changeVisibility(UpdatePlaylistRequest updatePlaylistRequest) {
        Playlist playlist = findPlaylistById(updatePlaylistRequest.getPlaylistId());
        User user = findUserById(updatePlaylistRequest.getUserId());

        if (!playlist.getUser().equals(user)) {
            throw new ResourceNotFoundException("You can't modify this playlist");
        }

        playlist.setStatus(playlist.getStatus() == PlaylistStatus.PRIVATE ? PlaylistStatus.SEARCHABLE : PlaylistStatus.PRIVATE);
        playlistRepository.save(playlist);
        return "Playlist visibility updated to " + playlist.getStatus();
    }

    @Override
    public String addSong(UpdateSongInPlaylistRequest updateSongInPlaylistRequest) {
        Playlist playlist = findPlaylistById(updateSongInPlaylistRequest.getPlaylistId());
        User user = findUserById(updateSongInPlaylistRequest.getUserId());
        SongInfo songInfo = findSongById(updateSongInPlaylistRequest.getSongId());

        if (!canUpdatePlaylist(user, playlist)) {
            throw new ResourceNotFoundException("You don't have permission to modify this playlist.");
        }

        boolean songExistsInPlaylist = songPlaylistRelationRepository.findByPlaylistAndSong(playlist.getId(), songInfo.getId()).isPresent();
        if (songExistsInPlaylist) {
            return "Song already exists in the playlist";
        }

        SongPlaylistRelation songPlaylistRelation = SongPlaylistRelation.builder()
                .playlist(playlist)
                .songInfo(songInfo)
                .build();

        songPlaylistRelationRepository.save(songPlaylistRelation);
        return "Song added to playlist";
    }

    @Override
    public String removeSong(UpdateSongInPlaylistRequest updateSongInPlaylistRequest) {
        Playlist playlist = findPlaylistById(updateSongInPlaylistRequest.getPlaylistId());
        User user = findUserById(updateSongInPlaylistRequest.getUserId());

        if (!canUpdatePlaylist(user, playlist)) {
            throw new ResourceNotFoundException("You don't have permission to modify this playlist.");
        }

        SongPlaylistRelation relation = songPlaylistRelationRepository.findByPlaylistAndSong(playlist.getId(), updateSongInPlaylistRequest.getSongId())
                .orElseThrow(() -> new ResourceNotFoundException("Song not found in the playlist"));

        songPlaylistRelationRepository.delete(relation);
        return "Song removed from playlist";
    }

    @Override
    public String updateStatus(UpdatePlaylistStatus updatePlaylistStatus) {
        Playlist playlist = findPlaylistById(updatePlaylistStatus.getPlaylistId());
        User user = findUserById(updatePlaylistStatus.getUserId());

        if (!playlist.getUser().equals(user)) {
            throw new ResourceNotFoundException("You don't have permission to modify this playlist.");
        }

        playlist.setStatus(updatePlaylistStatus.getPlaylistStatus());
        playlistRepository.save(playlist);
        return "Playlist status updated to " + updatePlaylistStatus.getPlaylistStatus();
    }

    private Playlist findPlaylistById(Long playlistId) {
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found"));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private SongInfo findSongById(Long songId) {
        return songInfoRepository.findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found"));
    }

    private boolean canUpdatePlaylist(User user, Playlist playlist) {
        return Objects.equals(user.getId(), playlist.getUser().getId()) ||
                (playlist.getStatus() != PlaylistStatus.PRIVATE && playlist.getPlaylistContributorsList().contains(user));
    }

    private boolean canAccessPlaylist(User user, Playlist playlist) {
        return Objects.equals(user.getId(), playlist.getUser().getId()) ||
                playlist.getStatus() != PlaylistStatus.PRIVATE ||
                playlist.getPlaylistContributorsList().contains(user);
    }
}
