package com.galvanize.movies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.movies.entities.Movie;
import com.galvanize.movies.repositories.MovieRepo;
import com.galvanize.movies.services.MovieService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    MovieService movieService;
    MovieRepo movieRepo;


    @Test
    void createdMovie() throws Exception {
        Movie movie1 = new Movie(1l, "Jackass");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(movie1);

        when(movieService.createMovie(ArgumentMatchers.any(Movie.class))).thenReturn(movie1);

        mvc.perform(post("/api/movies").content("{}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());


    }

    @Test
    void getMovieRequest() throws Exception {
        Movie expected = new Movie(1l, "jackass part two");
        ArrayList<Movie> expectedListOfMovies = new ArrayList<>();
        expectedListOfMovies.add(expected);

        when(movieService.getAllMovies()).thenReturn(expectedListOfMovies);

        mvc.perform(get("/api/movies").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(expectedListOfMovies.size())));
        //.andExpect(jsonPath("$[0].movie").value(expected.getMovie()));

    }

    @Test
    void getMovieById() throws Exception {
        Movie expected = new Movie(4l, "Jackass 2.0");
        ArrayList<Movie> listOfMovies = new ArrayList<>();
        listOfMovies.add(expected);

        when(movieService.getMovieById(4l)).thenReturn(expected);


        mvc.perform(get("/api/movies/4").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.movie").value(expected.getMovie()));

    }

    @Test
    void deleteMovieById() throws Exception {
        Movie expected = new Movie(1l, "Jackass5");
        when(movieService.deleteById(ArgumentMatchers.any(Long.class))).thenReturn(true);


        mvc.perform(delete("/api/movies/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
        verify(movieService).deleteById(1l);
    }
}
