package com.example.Spotify.service.impl;

import com.example.Spotify.dto.SongDTO;
import com.example.Spotify.exceptions.ResourceNotFoundException;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.repository.AlbumRepository;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.repository.SongInfoRepository;
import com.example.Spotify.service.SongService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;


@Service
public class SongServiceImpl implements SongService {

    private static final String songsLocation = "src/main/java/com/example/Spotify/model/songs/";
    private static final String songsCoverLocation = "src/main/java/com/example/Spotify/model/songs_cover/";


    private final SongInfoRepository songInfoRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public SongServiceImpl(SongInfoRepository songInfoRepository, AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.songInfoRepository = songInfoRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public SongInfo addSongInfo(String title) {

        return songInfoRepository.save(
            new SongInfo().builder()
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
        File songsDir = new File(songsLocation);
        File coverDir = new File(songsCoverLocation);

        if (!songsDir.exists())
            songsDir.mkdirs();

        if (!coverDir.exists())
            coverDir.mkdirs();

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
                new SongInfo().builder()
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


    @Transactional
    public SongInfo findSongByTitle(String title) {
        return songInfoRepository
                .findByTitle(title).stream()
                .findFirst()
                .orElse(null);
    }


    @Override
    public MultipartFile getSongImage(String url) throws IOException {
        try (FileInputStream fileReader = new FileInputStream(url)) {
            byte[] content = fileReader.readAllBytes();
//            String songName = url.split("/")[content.length-1];
            System.out.println(content.length);
//            System.out.println(songName);
            MultipartFile songCover =new MockMultipartFile(
                    "file",                              // The name of the parameter
                    new File(url).getName(),              // The original filename
                    "image/jpeg",                         // Content type (adjust based on the actual file type)
                    content                               // The content of the file
            );
            return songCover;
        } catch (IOException e) {
//            System.out.println("Song bad");
            throw e;
        }
    }

    @Override
    @Transactional
    public Resource play(long songId) {
        SongInfo songInfo = songInfoRepository.findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found"));

        Path songPath = Paths.get(songsLocation + songInfo.getSongURL());

        // Update play count
        songInfo.setPlayCount(songInfo.getPlayCount() + 1);
        songInfoRepository.save(songInfo);

        try {
            return (Resource) new UrlResource(songPath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error loading song file", e);
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


//    @Override
//    public SongInfo like(Long id) {
//        SongInfo songInfo = songRepository.findById(id).orElse(null);
//        if (songInfo == null)
//            throw new ResourceNotFoundException("Song not found");
//
//        if(songInfo.)
//        songRepository.save(songInfo.setLikes(songInfo.getLikes() + 1));
//
//        return songInfo;
//    }
//
//    @Override
//    public SongInfo dislike(long id) {
//        return null;
//    }


    /*
    public Resource play(long songId, long userId){}
    * */


}
