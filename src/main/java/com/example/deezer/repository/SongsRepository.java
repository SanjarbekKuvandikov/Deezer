package com.example.deezer.repository;

import com.example.deezer.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongsRepository  extends JpaRepository<Song,Long> {

}
