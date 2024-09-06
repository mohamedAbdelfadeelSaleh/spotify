package com.example.Spotify.service.impl;

import com.example.Spotify.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

@Service
public class FileServiceImpl implements FileService {

    public String storeFile(MultipartFile multipartFile, String location) {
        try {
            byte[] songBytes = multipartFile.getBytes();
            FileOutputStream songFileWriter = new FileOutputStream(location);
            songFileWriter.write(songBytes);
            songFileWriter.close();

        } catch (Exception e) {
            System.out.println("Bad File");
            ResponseEntity.badRequest();
        }
        return "Storing Success";
    }

    public Resource loadFileAsResource(String location) {
        return null;
    }
}
