package com.example.filmcompilation.repository;

import com.example.filmcompilation.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
