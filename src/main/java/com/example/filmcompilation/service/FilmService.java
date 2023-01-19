package com.example.filmcompilation.service;

import com.example.filmcompilation.model.Film;
import com.example.filmcompilation.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public Film getFilmById(Long id){
        return filmRepository.findById(id).orElse(null);
    }

    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }
}
