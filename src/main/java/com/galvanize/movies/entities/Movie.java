package com.galvanize.movies.entities;


import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String movie;

    public Movie() {
    }

    public Movie(long id, String movie) {
        this.id = id;
        this.movie = movie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movie='" + movie + '\'' +
                '}';
    }

}
