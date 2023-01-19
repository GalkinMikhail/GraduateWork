package com.example.filmcompilation.repository;

import com.example.filmcompilation.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FilmRepository extends JpaRepository<Film,Long> {
    Film findByTitle(String title);
}
