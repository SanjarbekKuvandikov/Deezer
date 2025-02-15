package com.example.deezer.service;

import com.example.deezer.dto.DeezerResponse;
import com.example.deezer.dto.SongDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeezerService {
public List<SongDTO> searchSongs(String query) {
    try {
        String apiURL = "https://api.deezer.com/search?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8);
        RestTemplate restTemplate = new RestTemplate();

        DeezerResponse response = restTemplate.getForObject(apiURL, DeezerResponse.class);

        //check response is not null and contains songs
        if (response == null ||response.getData() == null || response.getData().isEmpty()) {
            System.out.println("No data found");
            return List.of();
        }
        //convert API response to List
        return response.getData().stream()
                .map(song -> new SongDTO(song.getTitle(),
                        (song.getArtist() !=null) ? song.getArtist().getName() : "unkown"))
                .collect(Collectors.toList());

    }catch (Exception e){
        e.printStackTrace();
        System.out.println("Error" + e.getMessage());
        return List.of();
    }
}
}
