package net.chandol.study.showing.price.pricerule;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.showing.price.PriceRule;
import net.chandol.study.theater.Theater;
import org.junit.Test;

import java.time.LocalDateTime;


public class DefaultPriceRuleTest {

    @Test
    public void evaluate() throws Exception {
        //given
        PriceRule priceRule = new DefaultPriceRule();
        Movie movie = dummyMovie(MovieType.IMAX);
        LocalDateTime startTime = LocalDateTime.of(2016, 7, 7, 20, 20);

        //when
        Money price = priceRule.evaluate(
                Money.of(0), theater, movie, startTime
        );

        //then
        assertThat(price, is(7000));
    }

    public Movie dummyMovie(MovieType movieType){
        return new Movie("test", "test", 100, );
    }
}