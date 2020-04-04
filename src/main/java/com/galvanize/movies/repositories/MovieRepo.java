package com.galvanize.movies.repositories;

import com.galvanize.movies.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}
