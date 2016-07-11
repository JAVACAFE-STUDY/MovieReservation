package net.chandol.study.showing.price.rule;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.showing.price.PriceRule;
import net.chandol.study.theater.Theater;

import java.time.LocalDateTime;

public class TimeBasePriceRule implements PriceRule {
    @Override
    public Money execute(Money money, Theater theater, Movie movie, LocalDateTime showingTime) {
        return null;
    }
}
