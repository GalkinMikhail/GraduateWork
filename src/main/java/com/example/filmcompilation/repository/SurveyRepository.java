package com.example.filmcompilation.repository;

import com.example.filmcompilation.model.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SurveyRepository extends PagingAndSortingRepository<Survey,Long> {
    Survey getSurveyByTitle(String title);
}
