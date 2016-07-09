package net.chandol.study.movie;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    private int runningTime;

    @ElementCollection
    @CollectionTable(
            name = "MOVIE_AVAILABLE_TYPE",
            joinColumns = @JoinColumn(name = "MOVIE_ID")
    )
    private Set<MovieType> availableMovieType;

    protected Movie() {
    }

    Movie(String name, String description, int runningTime, Set<MovieType> availableMovieType) {
        this.name = name;
        this.description = description;
        this.runningTime = runningTime;
        this.availableMovieType = availableMovieType;
    }

}


@Repository
interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);
}
