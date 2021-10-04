package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Song;
import com.example.demo.repository.SongRepository;

@Service
public class SongService {

	@Autowired
	private SongRepository songRepository;
	
	public Song saveSong(Song song) {
		return this.saveSong(song);
	}
	
	public List<Song> getAllSongs() {
		return this.songRepository.findAll();
	}
	
	public Optional<Song> getSongById(Long id) {
		return this.songRepository.findById(id);
	}
}
