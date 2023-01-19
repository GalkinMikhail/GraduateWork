package com.example.filmcompilation.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 100000)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genre> genre;

    @Column(name = "releaseDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Column(name = "country")
    private String country;

    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id) && Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(genre, film.genre) && Objects.equals(releaseDate, film.releaseDate) && Objects.equals(country, film.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, genre, releaseDate, country);
    }
}
