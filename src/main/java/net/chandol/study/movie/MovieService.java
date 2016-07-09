package net.chandol.study.movie;

import net.chandol.study.movie.dto.MovieCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository repository;

    public Movie createMovie(MovieCreateRequest request) {
        Movie movie = new Movie(
                request.getName(),
                request.getDescription(),
                request.getRunningTime(),
                request.getMovieTypes()
        );

        return repository.save(movie);
    }
}


@Repository
interface MovieRepository extends JpaRepository<Movie, Long> {
}
