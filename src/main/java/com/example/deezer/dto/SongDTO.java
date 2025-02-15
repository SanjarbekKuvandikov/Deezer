package com.example.deezer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDTO {
    private final String title;
    private final String artist;

    public SongDTO(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }
}
