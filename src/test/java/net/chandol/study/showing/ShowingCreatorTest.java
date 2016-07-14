package net.chandol.study.showing;

import net.chandol.study._config.dummy.DummyDataGenerator;
import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.showing.dto.ShowingCreateRequest;
import net.chandol.study.theater.Theater;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Map;

import static net.chandol.study._testhelper.ExtendedObjectAssert.objectAssertThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowingCreatorTest {
    @Autowired DummyDataGenerator dummyDataGenerator;
    @Autowired ShowingCreator showingCreator;

    private Map<String, Theater> theaterMap;
    private Map<String, Movie> movieMap;

    @Before
    public void initData() {
        theaterMap = dummyDataGenerator.persistTheater();
        movieMap = dummyDataGenerator.persistMovie();
    }

    @Test
    public void create() throws Exception {
        // given
        Theater theater = theaterMap.get("서현점");
        Movie movie = movieMap.get("센과 치히로의 행방불명");

        LocalDateTime startTime = LocalDateTime.of(2016, 7, 9, 4, 20);

        // when
        Showing showing = showingCreator.create(new ShowingCreateRequest(theater, movie, MovieType._2D, startTime));

        // then
        objectAssertThat(showing)
                .is("price", Money.of(7000))
                .is("movie", movie)
                .is("theater", theater)
                .is("startTime", startTime);

        assertThat(showing.getTheater()).asList()
                .hasSize(theater.getSeats().size());
    }

}