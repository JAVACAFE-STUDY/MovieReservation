package net.chandol.study.showing.price;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.theater.Theater;

import java.time.LocalDateTime;

public interface PriceRule {
    Money evaluate(
            Money money, Theater theater, Movie movie, MovieType movieType, LocalDateTime showingTime
    );
}
