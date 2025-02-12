package com.example.deezer.controller;

import com.example.deezer.entity.Song;
import com.example.deezer.repository.SongsRepository;
import com.example.deezer.service.DeezerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongsRepository songsRepository;
    private final DeezerService deezerService;

    public SongController(SongsRepository songsRepository, DeezerService deezerService) {
        this.songsRepository = songsRepository;
        this.deezerService = deezerService;
    }

    //find song and save
    @PostMapping("/search")
    public Song searchAndSave(@RequestParam("query") String query){
        Song song = deezerService.searchSongs(query);
        return songsRepository.save(song);
    }

    @PutMapping("/{id}")
    public Song updateURL(@PathVariable Long id, @RequestParam String url){
        return songsRepository.findById(id).stream().map( song -> {
            song.setUrl(url);
            return songsRepository.save(song);
        }).findAny().orElse(null);
    }

    //Get all songs
    @GetMapping
    public List<Song> getAll(){
        return songsRepository.findAll();
    }

}
