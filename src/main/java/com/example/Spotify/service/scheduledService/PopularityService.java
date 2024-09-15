package com.example.Spotify.service.scheduledService;

import com.example.Spotify.model.*;
import com.example.Spotify.repository.AlbumRepository;
import com.example.Spotify.repository.ArtistRepository;
import com.example.Spotify.repository.PlaylistRepository;
import com.example.Spotify.repository.SongInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
public class PopularityService {
    private final SongInfoRepository songInfoRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final PlaylistRepository playlistRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateSongPopularity(){
        List<SongInfo> songInfoList = songInfoRepository.findAll();
        for(SongInfo temp : songInfoList){
            temp.setPopularity(
                    calcSongPopularity(
                            temp.getLikes(),
                            temp.getPlayCount(),
                            temp.getDislikes()
                    )
            );
            songInfoRepository.save(temp);
        }
        System.out.println("updating Song Popularity is done");
    }

    public void updateAlbumPopularity(){
        List<Album> albumList = albumRepository.findAll();
        for(Album temp : albumList){
            temp.setPopularity(temp.getSongInfo().stream()
                    .mapToInt(s->s.getPopularity())
                    .sum()
            );
            albumRepository.save(temp);
        }
        System.out.println("updating album Popularity is done");
    }

    public void updateArtistPopularity(){
        List<Artist> artistList = artistRepository.findAll();
        for(Artist temp : artistList){
            temp.setPopularity(temp.getSongInfos().stream()
                    .mapToInt(s -> s.getPopularity())
                    .sum()
            );
            artistRepository.save(temp);
        }
        System.out.println("updating artist Popularity is done");
    }

    public void updatePlaylistPopularity() {
        List<Playlist> playlistList = playlistRepository.findAll();
        for (Playlist playlist : playlistList) {
            int totalPopularity = 0;
            for (SongPlaylistRelation relation : playlist.getSongPlaylistRelation()) {
                SongInfo song = relation.getSongInfo();
                if (song != null) {
                    totalPopularity += song.getPopularity();
                }
            }
            playlist.setPopularity(totalPopularity);
            playlistRepository.save(playlist);
        }
    }

    private int calcSongPopularity (int likes, int playCount, int dislikes){
        return likes * 2 + playCount - dislikes;
    }

}
