package net.chandol.study.showing.price;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.showing.price.pricerule.DefaultPriceRule;
import net.chandol.study.theater.Theater;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class PriceCalculator {

    public Money calculate(Theater theater, Movie movie, MovieType movieType, LocalDateTime startTime) {
        List<PriceRule> priceRules = getPriceRules();

        Money money = Money.of(0);
        for (PriceRule rule : priceRules) {
            money = rule.evaluate(money, theater, movie, movieType, startTime);
        }

        return money;
    }

    private List<PriceRule> getPriceRules() {
        return Arrays.asList(new DefaultPriceRule());
    }
}
