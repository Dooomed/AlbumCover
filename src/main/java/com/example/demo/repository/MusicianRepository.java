package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Musician;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long>{

}
