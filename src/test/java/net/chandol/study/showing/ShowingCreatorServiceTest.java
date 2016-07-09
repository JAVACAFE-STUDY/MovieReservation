package net.chandol.study.showing;

import net.chandol.study._config.dummy.DummyDataGenerator;
import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.theater.Theater;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowingCreatorServiceTest {
    @Autowired
    DummyDataGenerator dummyDataGenerator;
    @Autowired ShowingCreatorService showingCreatorService;

    private Map<String, Theater> theaterMap;
    private Map<String, Movie> movieMap;

    @Before
    public void initData(){
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
        Showing showing = showingCreatorService.create(theater, movie, startTime, Money.of(3000));

        // then
        assertThat(showing.getPrice(), is(Money.of(3000)));
        assertThat(showing.getMovie(), is(movie));
        assertThat(showing.getTheater(), is(theater));
        assertThat(showing.getStartTime(), is(startTime));

        assertThat(theater.getSeats().size(), is(theater.getSeats().size()));
    }
}