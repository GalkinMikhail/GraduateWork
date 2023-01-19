package com.example.filmcompilation.repository;

import com.example.filmcompilation.model.Film;
import com.example.filmcompilation.model.User;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

//    @Query(nativeQuery = true,value = "SELECT * FROM filmCompilation.film JOIN user_recommendations_films ON film.id = user_recommendations_films.recommendations_films_id WHERE user_recommendations_films.user_id = ?1")
//    Set<Film> getRecs(Long id);

    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM users_recommendations_films WHERE recommendations_films_id = ?1")
    void deleteFilmFromRecs(Long id);
}
