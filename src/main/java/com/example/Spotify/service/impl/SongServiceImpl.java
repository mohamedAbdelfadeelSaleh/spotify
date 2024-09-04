package com.example.Spotify.service.impl;

import com.example.Spotify.dto.SearchResultDTO;
import com.example.Spotify.dto.SongDTO;
import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.LikeDislikeSong;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.model.User;
import com.example.Spotify.repository.*;
import com.example.Spotify.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import jakarta.transaction.Transactional;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private static final String songsLocation = "src/main/java/com/example/Spotify/model/songs/";
    private static final String songsCoverLocation = "src/main/java/com/example/Spotify/model/songs_cover/";


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
//        File songsDir = new File(songsLocation);
//        File coverDir = new File(songsCoverLocation);
//
//        if (!songsDir.exists())
//            songsDir.mkdirs();
//
//        if (!coverDir.exists())
//            coverDir.mkdirs();

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

    //this functions is not important
    @Override
    public void addSongAndCover2(SongDTO songDTO, MultipartFile songFile, MultipartFile coverImageFile) {
        System.out.println("addSongAndCover2 service is called ");
        addSongAndCover(songFile, coverImageFile, songDTO.getTitle());
    }

    @Override
    public SongInfo addSongInfo2(SongDTO songDTO) {
        System.out.println("addSongInfo2 Service is called");
        return songInfoRepository.save(
                SongInfo.builder()
                        .title(songDTO.getTitle())
                        .likes(0)
                        .dislikes(0)
                        .playCount(0)
                        .songURL(songsLocation + songDTO.getTitle() + ".mp3")
                        .songCoverURL(songsCoverLocation + songDTO.getTitle() + ".jpg")
                        .publishDate(new Date(System.currentTimeMillis()))
                        .isPremium(songDTO.isPremium())
                        .build()
        );
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
    //              .image(songImage)
                    .artist(songInfo.getArtist())
                    .songAlbum(songInfo.getAlbum())
                    .build();
    }

//    @Override
//    public SongInfo like(long songId, long userId) {
//        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(songId);
//        if(songInfoOpt.isEmpty()) {
//            System.out.println("Song Not found");
//            throw new ResourceNotFoundException("Song Not found");
//        }
//        SongInfo songInfo = songInfoOpt.get();
//        songInfo.setLikes(songInfo.getLikes() + 1);
//        Optional<User> userOpt = userRepository.findById(userId);
//        if(userOpt.isEmpty()) {
//            System.out.println("User Not found");
//            throw new ResourceNotFoundException("User Not found");
//        }
//        User user = userOpt.get();
//        Optional<LikeDislikeSong> likeDislikeSongOpt= likeDislikeSongRepository.findByUserAndSong(user, songInfo);
//        LikeDislikeSong likedDisLikeSong = new LikeDislikeSong();
//        if(likeDislikeSongOpt.isPresent()) {
//            likedDisLikeSong = likeDislikeSongOpt.get();
//        }
//
//        likedDisLikeSong.setUser(user);
//        likedDisLikeSong.setSongInfo(songInfo);
//        likedDisLikeSong.setFlag(true);
//        songInfoRepository.save(songInfo);
//        likeDislikeSongRepository.save(likedDisLikeSong);
//        return songInfo;
//    }
//
//    @Override
//    public SongInfo dislike(long songId, long userId) {
//        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(songId);
//        if(songInfoOpt.isEmpty()) {
//            System.out.println("Song Not found");
//            throw new ResourceNotFoundException("Song Not found");
//        }
//        SongInfo songInfo = songInfoOpt.get();
//        songInfo.setDislikes(songInfo.getDislikes() + 1);
//        Optional<User> userOpt = userRepository.findById(userId);
//        if(userOpt.isEmpty()) {
//            System.out.println("User Not found");
//            throw new ResourceNotFoundException("User Not found");
//        }
//        User user = userOpt.get();
//        Optional<LikeDislikeSong> likeDislikeSongOpt= likeDislikeSongRepository.findByUserAndSong(user, songInfo);
//        LikeDislikeSong likedDisLikeSong = new LikeDislikeSong();
//        if(likeDislikeSongOpt.isPresent()) {
//            likedDisLikeSong = likeDislikeSongOpt.get();
//        }
//
//        likedDisLikeSong.setUser(user);
//        likedDisLikeSong.setSongInfo(songInfo);
//        likedDisLikeSong.setFlag(false);
//        songInfoRepository.save(songInfo);
//        likeDislikeSongRepository.save(likedDisLikeSong);
//        return songInfo;
//    }

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











    @Override
    @Transactional
    public MultipartFile getSongImage(String url) throws IOException {
        try (FileInputStream fileReader = new FileInputStream(url)) {
            byte[] content = fileReader.readAllBytes();
//            String songName = url.split("/")[content.length-1];
            System.out.println(content.length);
//            System.out.println(songName);
            return new MockMultipartFile(
                    "file",                              // The name of the parameter
                    new File(url).getName(),              // The original filename
                    "image/jpeg",                         // Content type (adjust based on the actual file type)
                    content                               // The content of the file
            );
        }
    }







    @Override
    public String getFileName(Resource resource) {
        try {
            return Paths.get(resource.getURL().toURI()).getFileName().toString();
        } catch (Exception e) {
            throw new RuntimeException("Error getting file name", e);
        }
    }






    @Override
    public void updatePlayCount(long songId) {
        Optional<SongInfo> songInfoOpt = songInfoRepository.findById(songId);
        if(songInfoOpt.isEmpty()) {
           throw new ResourceNotFoundException("Song Not Found");
        }
        SongInfo songInfo = songInfoOpt.get();
        songInfo.setPlayCount(songInfo.getPlayCount() + 1);
    }

    @Override
    public Resource play(long songId) {
        SongInfo songInfo = songInfoRepository.findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found"));

        Path songPath = Paths.get(songsLocation + songInfo.getSongURL());

        // Update play count
        songInfo.setPlayCount(songInfo.getPlayCount() + 1);
        songInfoRepository.save(songInfo);

        try {
            return new UrlResource(songPath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error loading song file", e);
        }
    }


}
