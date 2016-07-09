package net.chandol.study.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;
import net.chandol.study.movie.MovieType;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieCreateRequest {
    private String name;
    private String description;
    private int runningTime;
    private Set<MovieType> movieTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public Set<MovieType> getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(MovieType... movieTypes) {
        this.movieTypes = Arrays.stream(movieTypes).collect(Collectors.toSet());
    }
}
