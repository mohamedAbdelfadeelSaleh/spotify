package com.example.Spotify.service.impl;


import com.example.Spotify.model.Album;
import com.example.Spotify.model.Artist;
import com.example.Spotify.model.SongInfo;
import com.example.Spotify.repository.AlbumRepository;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.repository.SongInfoRepository;
import com.example.Spotify.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private static final String albumCoverLocation = "src/main/java/com/example/Spotify/model/albums_cover/";

    private final SongInfoRepository songInfoRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;


    @Override
    public ResponseEntity<String> addAlbumCover(long artistId, String title, MultipartFile albumImageFile) {
        System.out.println(artistId);
        System.out.println("title " + title);
        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        if (!artistOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist not found");
        }
        Artist artist = artistOpt.get();
        System.out.println(artist.getName());

        Album album = new Album().builder()
                .name(title)
                .isPremium(false)
                .artist(artist)
                .coverURL(albumCoverLocation + title + ".jpg")
                .releaseDate(new Date(System.currentTimeMillis()))
                .build();
        albumRepository.save(album);
        System.out.println("adding to database is done");

        File coverDir = new File(albumCoverLocation);
        if (!coverDir.exists()) {
            coverDir.mkdirs();
        }
        System.out.println("creating file is done");

        try {
            byte[] imageBytes = albumImageFile.getBytes();
            FileOutputStream coverFileWriter = new FileOutputStream(albumCoverLocation + title + ".jpg");
            coverFileWriter.write(imageBytes);
            coverFileWriter.close();
        } catch (Exception e) {
            System.out.println("Image bad");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to upload album cover");
        }

        return ResponseEntity.ok("Album cover is uploaded");
    }

    @Override
    public ResponseEntity<String> deleteAlbum(long artistId, String title){

        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        if (artistOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist not found");
        }
        Optional<Album> albumOpt = albumRepository.findById(artistId);
        if (albumOpt.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album not found");
        }
        Album album = albumOpt.get();
        List<SongInfo> songInfos = album.getSongInfo();
        for (SongInfo songInfo : songInfos) {
            songInfoRepository.delete(songInfo);
        }
        albumRepository.delete(album);
        return ResponseEntity.ok().body("deleted successfully");
    }

}
