package com.galvanize.movies.services;

import com.galvanize.movies.entities.Movie;
import com.galvanize.movies.repositories.MovieRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class serviceTest {
    @Autowired
    MovieRepo movieRepo;
    MovieService movieService;
    @BeforeEach
    void setUp(){
        movieService = new MovieService(movieRepo);
    }
    @Test
    void createMovie_ReturnsMovie(){

        Movie movie1 = new Movie(1l, "jackassa");
        Movie expected = movieService.createMovie(movie1);// this saves movie
        assertNotNull(expected);

    }
    @Test
    void ableToReturnAllMovies(){
        Movie movie = new Movie(1l,"jackass3");
        movieRepo.save(movie);

        List<Movie> actual = movieService.getAllMovies();

        assertEquals(1,actual.size());
    }

    @Test
    void ableToReturnById(){
      Movie movie = movieService.createMovie(new Movie(1l,"jackass5"));

        boolean actual = movieService.deleteById(movie.getId());

        assertEquals(true, actual);

    }
    @Test
    void deleteById(){
        Movie movie = movieService.createMovie(new Movie(1l,"jackass 6"));
        boolean actual = movieService.deleteById(movie.getId());

        assertEquals(true, actual);


    }
}
