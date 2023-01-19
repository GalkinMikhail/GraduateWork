package com.example.filmcompilation.service;

import com.example.filmcompilation.model.*;
import com.example.filmcompilation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private UserRepository userRepository;

    public Survey getPrimarySurvey(User user){
        Survey survey = new Survey();
        survey.setTitle("Первичный опрос пользователя" + user.getUsername());
        Question question1 = new Question();
        question1.setQuestion("Выберите свой любимый жанр кино:");
        questionRepository.save(question1);
        Question question2 = new Question();
        question1.setQuestion("Выберите период, фильмы которого вам нравятся больше");
        questionRepository.save(question2);
        Question question3 = new Question();
        question3.setQuestion("Вам больше нравится отечественный кинематограф или западный?");
        questionRepository.save(question3);
        survey.setQuestionList(List.of(question1,question2,question3));
        surveyRepository.save(survey);
        return survey;
    }
    public void passPrimarySurvey(User user, String genre, String dateFrom, String dateFor, String country){
        Survey survey = surveyRepository.getSurveyByTitle("Первичный опрос пользователя" + user.getUsername());
        Set<Film> recommendations = new HashSet<>();

        if (genreRepository.findByName(genre).getName().equals("Боевик")){
            List<Film> genreBy = new ArrayList<>();
            List<Film> allFilms = new ArrayList<>(filmRepository.findAll());
            for (Film film: allFilms) {
                if (film.getGenre().contains(genreRepository.findByName("Боевик"))){
                    genreBy.add(film);
                }
            }
            recommendations.addAll(genreBy);
        }
        if (genreRepository.findByName(genre).getName().equals("Драма")){
            List<Film> genreBy = new ArrayList<>();
            List<Film> allFilms = new ArrayList<>(filmRepository.findAll());
            for (Film film: allFilms) {
                if (film.getGenre().contains(genreRepository.findByName("Драма"))){
                    genreBy.add(film);
                }
            }
            recommendations.addAll(genreBy);
        }
        if (genreRepository.findByName(genre).getName().equals("Детектив")){
            List<Film> genreBy = new ArrayList<>();
            List<Film> allFilms = new ArrayList<>(filmRepository.findAll());
            for (Film film: allFilms) {
                if (film.getGenre().contains(genreRepository.findByName("Детектив"))){
                    genreBy.add(film);
                }
            }
            recommendations.addAll(genreBy);
        }
        if (genreRepository.findByName(genre).getName().equals("Ужасы")){
            List<Film> genreBy = new ArrayList<>();
            List<Film> allFilms = new ArrayList<>(filmRepository.findAll());
            for (Film film: allFilms) {
                if (film.getGenre().contains(genreRepository.findByName("Ужасы"))){
                    genreBy.add(film);
                }
            }
            recommendations.addAll(genreBy);
        }
        if (genreRepository.findByName(genre).getName().equals("Комедия")){
            List<Film> genreBy = new ArrayList<>();
            List<Film> allFilms = new ArrayList<>(filmRepository.findAll());
            for (Film film: allFilms) {
                if (film.getGenre().contains(genreRepository.findByName("Комедия"))){
                    genreBy.add(film);
                }
            }
            recommendations.addAll(genreBy);
        }

        if (country.equals("Отечественные")){
            recommendations.removeIf(film -> !film.getCountry().contains("Россия"));
        }
        else{
            recommendations.removeIf(film -> film.getCountry().equals("Россия"));
        }

        recommendations.removeIf(film -> film.getReleaseDate().getYear() < (Integer.parseInt(dateFrom)) && film.getReleaseDate().getYear() > (Integer.parseInt(dateFor)));

        user.setRecommendationsFilms(recommendations);
        userRepository.save(user);
    }

    @Transactional
    public void addToWatchedFilms(User user,List<Film> films){
        for (Film film:films) {
            userRepository.deleteFilmFromRecs(film.getId());
        }
        Set<Film> recs = new HashSet<>(user.getRecommendationsFilms());
        for (Film film:films) {
            recs.removeIf(film1 -> film1.getTitle().equals(film.getTitle()));
        }
        user.setRecommendationsFilms(recs);
        userRepository.save(user);
        Set<Film> watched = new HashSet<>(user.getWatchedFilms());
        watched.addAll(films);
        user.setWatchedFilms(watched);
        userRepository.save(user);
    }

    @Transactional
    public void alreadySeen(User user,Long id){
        userRepository.deleteFilmFromRecs(id);
        Film film = filmRepository.getById(id);
        Set<Film> recs = new HashSet<>(user.getRecommendationsFilms());
        recs.removeIf(film1 -> film1.getTitle().equals(film.getTitle()));
        user.setRecommendationsFilms(recs);
        userRepository.save(user);
        Set<Film> watched = new HashSet<>(user.getWatchedFilms());
        watched.add(film);
        user.setWatchedFilms(watched);
        userRepository.save(user);
    }

}
