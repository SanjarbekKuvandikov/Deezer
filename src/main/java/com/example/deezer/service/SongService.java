package com.example.deezer.service;

import com.example.deezer.entity.Song;
import com.example.deezer.repository.SongsRepository;
import org.springframework.stereotype.Service;


@Service
public class SongService {
    public final SongsRepository songsRepository;

    public SongService(SongsRepository songsRepository) {
        this.songsRepository = songsRepository;
    }

    public void updateSongUrl(Long id, String url){
        Song song = songsRepository.findById(id).orElse(null);
        if (song != null){
            song.setUrl(url);
            songsRepository.save(song);
        }
    }
}
