package net.chandol.study.movie;

import net.chandol.study.movie.dto.MovieCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static net.chandol.study._config.dummy.DummyDataGenerator.getMovieCreateRequests;
import static net.chandol.study._testhelper.ExtendedObjectAssert.objectAssertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {
    @Autowired MovieService service;

    @Test
    public void createMovie() throws Exception {
        //given
        MovieCreateRequest request = getMovieCreateRequests().get("WALL-E");

        //when
        Movie movie = service.create(request);

        //then
        objectAssertThat(movie)
                .is("name", "WALL-E")
                .is("runningTime", 104)
                .hasFieldOrProperty("description");
    }
}