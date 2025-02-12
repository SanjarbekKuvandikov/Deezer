package com.example.deezer.controller;

import com.example.deezer.entity.Song;
import com.example.deezer.repository.SongsRepository;
import com.example.deezer.service.DeezerService;
import com.example.deezer.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongsRepository songsRepository;
    private final DeezerService deezerService;
    private final SongService songService;

    public SongController(SongsRepository songsRepository, DeezerService deezerService, SongService songService) {
        this.songsRepository = songsRepository;
        this.deezerService = deezerService;
        this.songService = songService;
    }

    //find song and save
    @PostMapping("/search")
    public Song searchAndSave(@RequestParam("query") String query){
        Song song = deezerService.searchSongs(query);
        return songsRepository.save(song);
    }

    //Get all songs
    @GetMapping
    public List<Song> getAll(){
        return songsRepository.findAll();
    }

    //put url
    @PutMapping("/{id}")
    public ResponseEntity<String> updateURL(@PathVariable Long id, @RequestParam String url){
        songService.updateSongUrl(id,url);
        return ResponseEntity.ok("Song`s url updated successfully");
    }

    //getting no url
    @GetMapping("/no-url")
    public List<Song> getSongsWithoutUrl(){
     return songsRepository.findByUrlIsNullOrUrl("");
    }

}
