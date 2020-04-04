package com.galvanize.movies.services;

import com.galvanize.movies.entities.Movie;
import com.galvanize.movies.repositories.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public Movie createMovie(Movie movies) {
        return movieRepo.save(movies);
    }

    public List<Movie>  getAllMovies() {
        return  movieRepo.findAll();
    }

     public Movie getMovieById(long id) {
        Optional<Movie> optionalMovie = movieRepo.findById(id);
        return optionalMovie.orElse(null);
    }

    public boolean deleteById(long id) {
        movieRepo.deleteById(id);
        return true;
    }
}
