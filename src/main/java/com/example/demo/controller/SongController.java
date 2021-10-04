package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Song;
import com.example.demo.service.SongService;

@RestController
@RequestMapping("/song")
@CrossOrigin(origins = "http://localhost:4200")
public class SongController {
	
	@Autowired
	private SongService songService;

	@PostMapping("/save")
	private ResponseEntity<Song> saveSong(@RequestBody Song song) {
		return new ResponseEntity<>(songService.saveSong(song), HttpStatus.OK);
	}
	
}
