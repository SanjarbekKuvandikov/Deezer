package com.example.deezer.repository;

import com.example.deezer.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongsRepository  extends JpaRepository<Song,Long> {
    List<Song> findByUrlIsNullOrUrl(String url);
}
