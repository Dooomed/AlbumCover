package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Album;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{

    List<Album> getAlbumsByMusician_Id(Long id);
}
