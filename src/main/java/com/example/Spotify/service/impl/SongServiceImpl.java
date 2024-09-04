package com.example.Spotify.service.impl;

import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.LikedDislikedSong;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.model.User;
import com.example.Spotify.repository.*;
import com.example.Spotify.service.SongService;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private static final String songsLocation = "src/main/java/com/example/Spotify/model/songs/";
    private static final String songsCoverLocation = "src/main/java/com/example/Spotify/model/songs_cover/";

    private final UserRepository userRepository;
    private final SongInfoRepository songInfoRepository;
    private final LikeDislikeSongRepository likeDislikeSongRepository;

    @Override
    public SongInfo addSongInfo(String title) {
        new SongInfo();
        return songInfoRepository.save(
            SongInfo.builder()
                    .title(title)
                    .likes(0)
                    .songCoverURL(songsLocation + title + ".jpg")
                    .songURL(songsCoverLocation + title + ".mp3")
                    .likes(0)
                    .dislikes(0)
                    .playCount(0)
                    .publishDate(new Date(System.currentTimeMillis()))
                    .isPremium(false)
                    .build()

        );
    }

    public void addSongAndCover(MultipartFile songFile, MultipartFile coverImageFile, String name) {
        try {
            byte[] songBytes = songFile.getBytes();
            FileOutputStream songFileWriter = new FileOutputStream(songsLocation + name + ".mp3");
            songFileWriter.write(songBytes);
            songFileWriter.close();

        } catch (Exception e) {
            System.out.println("Song bad");
            ResponseEntity.badRequest();
        }
        try {
            byte[] imageBytes = coverImageFile.getBytes();
            FileOutputStream coverFileWriter = new FileOutputStream(songsCoverLocation + name + ".jpg");
            coverFileWriter.write(imageBytes);
            coverFileWriter.close();
        } catch (Exception e) {
            System.out.println("Image bad");
            ResponseEntity.badRequest();
        }
    }


    @Override
    @Transactional
    public SearchResultDTO findSongByTitle(String title) {
        System.out.println("find Song By Title serviceis called");
        Optional<List<SongInfo>> songInfoListOpt = songInfoRepository.findByTitle(title);
        if (songInfoListOpt.isEmpty()) {
            System.out.println("song info not found");
            throw new ResourceNotFoundException("song info not found");
        }
        List<SongInfo> songInfoList = songInfoListOpt.get();
        System.out.println(songInfoList);
        songInfoList.stream().sorted((s1,s2) -> s2.getPlayCount() - s1.getPlayCount());
        System.out.println(songInfoList);
        SongInfo songInfo = songInfoList.get(0);
        return  SearchResultDTO.builder()
                    .songs(songInfoList)
                    .artist(songInfo.getArtist())
                    .songAlbum(songInfo.getAlbum())
                    .build();
    }

    @Override
    public SongInfo like(long songId, long userId) {
        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(songId);
        if(songInfoOpt.isEmpty()) {
            System.out.println("Song Not found");
            throw new ResourceNotFoundException("Song Not found");
        }
        SongInfo songInfo = songInfoOpt.get();
        songInfo.setLikes(songInfo.getLikes() + 1);
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            System.out.println("User Not found");
            throw new ResourceNotFoundException("User Not found");
        }
        User user = userOpt.get();
        Optional<LikedDislikedSong> likeDislikeSongOpt= likeDislikeSongRepository.findByUserAndSongInfo(user.getId(), songInfo.getId());
        LikedDislikedSong likedDisLikeSong = new LikedDislikedSong();
        if(likeDislikeSongOpt.isPresent()) {
            likedDisLikeSong = likeDislikeSongOpt.get();
        }

        likedDisLikeSong.setUser(user);
        likedDisLikeSong.setSongInfo(songInfo);
        likedDisLikeSong.setFlag(true);
        songInfoRepository.save(songInfo);
        likeDislikeSongRepository.save(likedDisLikeSong);
        return songInfo;
    }

    @Override
    public SongInfo dislike(long songId, long userId) {
        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(songId);
        if(songInfoOpt.isEmpty()) {
            System.out.println("Song Not found");
            throw new ResourceNotFoundException("Song Not found");
        }
        SongInfo songInfo = songInfoOpt.get();
        songInfo.setDislikes(songInfo.getDislikes() + 1);
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            System.out.println("User Not found");
            throw new ResourceNotFoundException("User Not found");
        }
        User user = userOpt.get();
        Optional<LikedDislikedSong> likeDislikeSongOpt= likeDislikeSongRepository.findByUserAndSongInfo(user.getId(), songInfo.getId());
        LikedDislikedSong likedDisLikeSong = new LikedDislikedSong();
        if(likeDislikeSongOpt.isPresent()) {
            likedDisLikeSong = likeDislikeSongOpt.get();
        }
        likedDisLikeSong.setUser(user);
        likedDisLikeSong.setSongInfo(songInfo);
        likedDisLikeSong.setFlag(false);
        songInfoRepository.save(songInfo);
        likeDislikeSongRepository.save(likedDisLikeSong);
        return songInfo;
    }

    @Override
    public List<SongInfo> getUserLikedSongs(long userId) {
        List<SongInfo> likedSongs = likeDislikeSongRepository.findLikedSongsByUserId(userId);
        System.out.println(likedSongs);
        return likedSongs;
    }

    @Override
    public List<SongInfo> getUserDislikedSongs(long userId) {
        List<SongInfo> dislikedSongs = likeDislikeSongRepository.findDislikedSongsByUserId(userId);
        System.out.println(dislikedSongs);
        return dislikedSongs;
    }


}
