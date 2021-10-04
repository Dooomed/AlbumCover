package com.example.demo.repository;

import com.example.demo.model.Album;
import com.example.demo.model.Musician;
import com.example.demo.model.Song;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired MusicianRepository musicianRepository;

    @Test
    public void saveAlbum() {
        Musician musician = new Musician("Drake", "Rap artist", "cover");
        musicianRepository.save(musician);
        Album album = new Album("CLB", musician, List.of(
        new Song("Too Sexy", "url", musician
        )), "cover");

        album.getSongList().forEach(e -> e.setAlbum(album));

        //this.albumRepository.save(album);
    }


}