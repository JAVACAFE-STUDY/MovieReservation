package net.chandol.study.showing.price;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.showing.price.pricerule.CultureDayPriceRule;
import net.chandol.study.showing.price.pricerule.DefaultPriceRule;
import net.chandol.study.theater.Theater;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

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

    // 가격정책을 가져옵니다.
    private List<PriceRule> getPriceRules() {
        return asList(
                new DefaultPriceRule(),
                new CultureDayPriceRule()
        );
    }

}
