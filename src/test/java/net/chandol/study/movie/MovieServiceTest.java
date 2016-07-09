package net.chandol.study.movie;

import net.chandol.study.movie.dto.MovieCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static net.chandol.study._config.dummy.DummyDataCreator.getMovieCreateRequests;
import static net.chandol.study.movie.MovieType.NORMAL;
import static net.chandol.study.movie.MovieType._3D;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {
    @Autowired
    MovieService service;

    @Test
    public void createMovie() throws Exception {
        //given
        MovieCreateRequest request = getMovieCreateRequests().get("WALL-E");

        //when
        Movie movie = service.createMovie(request);

        //then
        assertThat(movie.getName(), is("WALL-E"));
        assertThat(movie.getRunningTime(), is(104));
        assertThat(movie.getDescription(), is(notNullValue()));
        assertThat(movie.getAvailableMovieType(), hasItems(NORMAL, _3D));
    }
}