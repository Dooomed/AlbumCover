package com.example.demo.repository;

import com.example.demo.model.Musician;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MusicianRepositoryTest {

    @Autowired
    private MusicianRepository musicianRepository;

    @Test
    public void saveMusician(){
        Musician musician = new Musician(
                "Drake", "Rap artist", "https://upload.wikimedia.org/wikipedia/commons/1/15/Drake_at_The_Carter_Effect_2017_%2836818935200%29_%28cropped%29.jpg");
        this.musicianRepository.save(musician);
    }
}