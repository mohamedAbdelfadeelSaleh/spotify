package com.example.Spotify.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String storeFile(MultipartFile multipartFile, String location);
    Resource loadFileAsResource(String location);

}
