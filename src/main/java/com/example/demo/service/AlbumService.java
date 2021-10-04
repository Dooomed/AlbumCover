package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Album;
import com.example.demo.repository.AlbumRepository;


@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	public Album saveAlbum(Album album) {
		return albumRepository.save(album);
	}
	
	public List<Album> findAll() {
		return albumRepository.findAll();
	}
	
	public Optional<Album> findAlbumById(Long id) {
		return albumRepository.findById(id);
	}

	public List<Album> getAlbumsByMusicianId(Long id)  {
		return this.albumRepository.getAlbumsByMusician_Id(id);
	}

	public void updateAlbum(Album album) {
		this.albumRepository.save(album);
	}


}
