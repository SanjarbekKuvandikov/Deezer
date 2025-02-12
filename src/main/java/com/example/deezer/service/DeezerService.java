package com.example.deezer.service;

import com.example.deezer.entity.Song;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class DeezerService {
public Song searchSongs(String query) {
    try {
        String apiURL = "https://api.deezer.com/search?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8);
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(apiURL, String.class);

        if (response == null){
            System.out.println("API is empty");
            return null;
        }

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        //data is existed
        if (!jsonObject.has("data") || jsonObject.get("data").isJsonNull()){
            System.out.println("Json data is null");
            return null;
        }

        JsonArray data = jsonObject.getAsJsonArray("data");

        if (data.isEmpty()){
            System.out.println("Json data is empty");
            return null;
        }

            JsonObject firstSong = data.get(0).getAsJsonObject();

            String title = firstSong.has("title") ? firstSong.get("title").getAsString() : "null";
            String author = firstSong.has("artist") && firstSong.getAsJsonObject("artist").has("name")
                    ? firstSong.getAsJsonObject("artist").get("name").getAsString()
                    : "null";
            return new Song(title, author, "");

    }catch (Exception e){
        e.printStackTrace();
        System.out.println("Error" + e.getMessage());
        return null;
    }
}
}
