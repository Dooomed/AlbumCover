package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.Musician;
import com.example.demo.model.Song;
import com.example.demo.service.AlbumService;
import com.example.demo.service.MusicianService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/album")
@CrossOrigin(origins = "http://localhost:4200")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private MusicianService musicianService;

    @GetMapping("/musician/{id}")
    public ResponseEntity<List<Album>> getByMusicianId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(albumService.getAlbumsByMusicianId(id), HttpStatus.OK);
    }

    @PostMapping("/album/save")
    public ResponseEntity<Album> saveAlbum(@RequestBody AlbumDto albumDto) {

        List<SongDto> songsDto = albumDto.getSongs();
        if(songsDto.isEmpty()) throw new IllegalStateException("Song list is empty or null.");

        List<Song> songs = new ArrayList<>();

        Musician musician = this.musicianService.getMusicianById(albumDto.getMusicianId()).orElseThrow(() -> {
            throw new IllegalStateException("Musician is Empty!!!");
        });

        for(SongDto songDto: songsDto) {
            songs.add(new Song(
                    songDto.name, songDto.songUrl, musician
            ));
        };

        Album album = new Album(albumDto.name, musician, songs, albumDto.coverUrl);

        this.albumService.saveAlbum(album);

        return new ResponseEntity<>(album, HttpStatus.OK);
    }


    @Data
    public static class AlbumDto {

        private Long id;
        private String name;
        private Long musicianId;
        private List<SongDto> songs;
        private String coverUrl;

        public AlbumDto() {

        }

        public AlbumDto(String name, Long musicianId, List<SongDto> songs, String coverUrl) {
            this.name = name;
            this.musicianId = musicianId;
            this.songs = songs;
            this.coverUrl = coverUrl;
        }
    }

    @Data
    @AllArgsConstructor
    public static class SongDto {

        private Long id;
        private String name;
        private String songUrl;
        private Long albumId;
        private Long musicianId;

        public SongDto(String name, String songUrl, Long albumId, Long musicianId) {
            this.name = name;
            this.songUrl = songUrl;
            this.albumId = albumId;
            this.musicianId = musicianId;
        }

        public SongDto() {

        }
    }
}
