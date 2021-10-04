package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Musician implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	private String imageUrl;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Album> albumList;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Song> songs;

	public Musician(String name, String description, String imageUrl) {
		super();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Musician musician = (Musician) o;

		return Objects.equals(id, musician.id);
	}

	@Override
	public int hashCode() {
		return 1461707573;
	}
}
