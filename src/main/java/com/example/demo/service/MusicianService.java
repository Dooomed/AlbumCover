package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Musician;
import com.example.demo.repository.MusicianRepository;


@Service
public class MusicianService {

	
	@Autowired
	private MusicianRepository musicianRepository;
	
	public List<Musician> getAllMusicians() {
		return musicianRepository.findAll();
	}
	
	public Optional<Musician> getMusicianById(Long id) {
		return musicianRepository.findById(id);
	}
	
	public Musician saveMusician(Musician musician) {
		return musicianRepository.save(musician);
	}
	
	public void deleteMusician(Long id) {
		this.musicianRepository.deleteById(id);
	}

	
}
