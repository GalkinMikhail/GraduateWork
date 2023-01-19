package com.example.filmcompilation.repository;

import com.example.filmcompilation.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    Genre findByName(String name);
}
