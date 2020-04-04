package com.galvanize.movies.controllers;

import com.galvanize.movies.entities.Movie;
import com.galvanize.movies.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    ResponseEntity<Object> createMovie(@RequestBody Movie movie){
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping
    ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

@GetMapping("/{id}")
    ResponseEntity<Movie> getMovieById(Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));

}

}
