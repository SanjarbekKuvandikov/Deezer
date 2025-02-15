package com.example.deezer.controller;

import com.example.deezer.dto.SongDTO;
import com.example.deezer.entity.Song;
import com.example.deezer.repository.SongsRepository;
import com.example.deezer.service.DeezerService;
import com.example.deezer.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<SongDTO> searchAndSave(@RequestParam("query") String query){
        return deezerService.searchSongs(query);
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

    @PostMapping("/save")
    public ResponseEntity<String> saveSong(@RequestBody Map<String, String> requestBody){
        String title = requestBody.get("title");
        String artist = requestBody.get("artist");
        String url = requestBody.get("url");

        if (title == null || artist == null || url == null){
            return ResponseEntity.badRequest().body("Title,artist and url are required");
        }
        Song song = new Song(title, artist, url);
        songsRepository.save(song);
        return ResponseEntity.ok("Song saved successfully");
    }

}
