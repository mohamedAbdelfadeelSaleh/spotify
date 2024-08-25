package com.example.Spotify.service.impl;

import com.example.Spotify.model.SongInfo;
import com.example.Spotify.repository.SongRepository;
import com.example.Spotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;


@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;


    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public SongInfo addSongInfo(String title) {

        return songRepository.save(
            new SongInfo().builder()
                    .title(title)
                    .likes(0)
                    .songCoverURL("covers/" + title + ".jpg")
                    .songURL("songs/" + title + ".mp3")
                    .build()

        );
    }


    public void addSongAndCover(MultipartFile songFile, MultipartFile coverImageFile, String name){

        File songsDir = new File("songs/");
        File coverDir = new File("covers/");

        if (!songsDir.exists()) {
            songsDir.mkdirs(); // Creates the directory if it doesn't exist
        }

        if (!coverDir.exists()) {
            coverDir.mkdirs(); // Creates the directory if it doesn't exist
        }

        try {

            // Handle song file (save to database, file system, etc.)
            byte[] songBytes = songFile.getBytes();


            FileOutputStream songFileWriter = new FileOutputStream("songs/" + name + ".mp3");
            songFileWriter.write(songBytes);
            songFileWriter.close();

//            return ResponseEntity.ok("Files uploaded successfully");
        } catch (Exception e) {
            System.out.println("Song bad");
            ResponseEntity.badRequest();
        }


        try {
            byte[] imageBytes = coverImageFile.getBytes();

            // Handle the image bytes (e.g., save to file system)
            FileOutputStream coverFileWriter = new FileOutputStream("covers/" + name + ".jpg");
            coverFileWriter.write(imageBytes);
            coverFileWriter.close();
//            System.out.println("coverIsDone");

//            return ResponseEntity.ok("Files uploaded successfully");
        } catch (Exception e) {
            System.out.println("Image bad");
            ResponseEntity.badRequest();
        }

    }
}
