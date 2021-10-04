package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Album;
import com.example.demo.model.Song;
import com.example.demo.service.AlbumService;
import com.example.demo.service.SongService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Musician;
import com.example.demo.service.MusicianService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/musician")
public class MusicianController {

	@Autowired
	private MusicianService musicianService;
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/{id}")
	public ResponseEntity<MusicianDto> showOne(@PathVariable("id") Long id){
		Musician musician = musicianService.getMusicianById(id).orElseThrow(() -> new IllegalStateException("Musician not found"));
		MusicianDto musicianDto = new MusicianDto(musician.getId(), musician.getName(), musician.getDescription(),
				albumService.getAlbumsByMusicianId(id), musician.getImageUrl());
		return new ResponseEntity<>(musicianDto, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Musician>> showAll() {
		return new ResponseEntity<>(musicianService.getAllMusicians(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Musician> save(@RequestBody Musician musician){
		return new ResponseEntity<>(musicianService.saveMusician(musician), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.musicianService.deleteMusician(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@AllArgsConstructor
	@Data
	public static class MusicianDto {
		private Long id;
		private String name;
		private String description;
		private List<Album> albums;
		private String imageUrl;
		//private List<Song> songs;
	}
}
