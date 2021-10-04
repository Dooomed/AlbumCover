package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String songUrl;
	
	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	private Album album;

	@ManyToOne(optional = false)
	private Musician musician;

	public Song(String name, String songUrl, Musician musician) {
		this.name = name;
		this.songUrl = songUrl;
		this.musician = musician;
	}
}
