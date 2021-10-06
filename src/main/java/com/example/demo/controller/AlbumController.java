package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.Musician;
import com.example.demo.model.Song;
import com.example.demo.service.AlbumService;
import com.example.demo.service.MusicianService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/album")
@CrossOrigin()
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private MusicianService musicianService;

    @GetMapping("/musician/{id}")
    public ResponseEntity<List<Album>> getByMusicianId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(albumService.getAlbumsByMusicianId(id), HttpStatus.OK);
    }

    @PostMapping("/save")
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
        }

        Album album = new Album(albumDto.name, musician, songs, albumDto.coverUrl);

        this.albumService.saveAlbum(album);

        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> findAlbumById(@PathVariable("id") Long id) {
        Album album = this.albumService.findAlbumById(id).orElseThrow(
                () -> new IllegalStateException("Album not found!"));
        List<SongDto> songDtoList = new ArrayList<>();
        album.getSongList().forEach(e -> songDtoList.add(new SongDto(e.getId(), e.getName(), e.getSongUrl(), album.getId(), e.getMusician().getId())));
        AlbumDto albumDto = new AlbumDto(album.getId(), album.getName(), album.getMusician().getId(), songDtoList, album.getCoverUrl());

        return new ResponseEntity<>(albumDto, HttpStatus.OK);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AlbumDto {

        private Long id;
        private String name;
        private Long musicianId;
        private List<SongDto> songs;
        private String coverUrl;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SongDto {

        private Long id;
        private String name;
        private String songUrl;
        private Long albumId;
        private Long musicianId;

    }
}
