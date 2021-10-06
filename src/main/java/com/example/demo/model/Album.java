package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@ManyToOne(optional = false)
	private Musician musician;

	private LocalDateTime releaseDate;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Song> songList;
	
	private String coverUrl;

	public Album(String name, Musician musician, List<Song> songList, String coverUrl) {
		super();
		this.name = name;
		this.musician = musician;
		this.releaseDate = LocalDateTime.now();
		this.songList = songList;
		this.coverUrl = coverUrl;
	}

}
