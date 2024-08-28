package com.example.Spotify.exceptions;

import java.io.IOException;

public class FileStorageException extends IOException {
    public FileStorageException(String message) {
        super(message);
    }

}
