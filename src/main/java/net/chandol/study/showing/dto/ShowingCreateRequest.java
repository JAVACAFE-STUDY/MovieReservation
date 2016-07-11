package net.chandol.study.showing.dto;

import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.theater.Theater;

import java.time.LocalDateTime;

public class ShowingCreateRequest {
    private final Theater theater;
    private final Movie movie;
    private final MovieType movieType;
    private final LocalDateTime startTime;

    public ShowingCreateRequest(Theater theater, Movie movie, MovieType movieType, LocalDateTime startTime) {
        this.theater = theater;
        this.movie = movie;
        this.movieType = movieType;
        this.startTime = startTime;
    }

    public Theater getTheater() {
        return theater;
    }

    public Movie getMovie() {
        return movie;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
