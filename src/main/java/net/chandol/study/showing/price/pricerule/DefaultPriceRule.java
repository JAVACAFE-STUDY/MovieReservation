package net.chandol.study.showing.price.pricerule;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.showing.price.PriceRule;
import net.chandol.study.theater.Theater;

import java.time.LocalDateTime;

public class DefaultPriceRule implements PriceRule {
    @Override
    public Money evaluate(Money money, Theater theater, Movie movie, LocalDateTime showingTime){
        return evaluate(movie.getType(), showingTime);
    }

    Money evaluate(LocalDateTime dateTime, MovieType type) {
        //do something
        return null;
    }
}

