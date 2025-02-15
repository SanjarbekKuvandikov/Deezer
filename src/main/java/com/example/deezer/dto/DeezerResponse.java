package com.example.deezer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DeezerResponse {
    private List<SongData> data;

}
