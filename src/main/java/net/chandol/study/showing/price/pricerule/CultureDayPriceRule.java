package net.chandol.study.showing.price.pricerule;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.showing.price.PriceRule;
import net.chandol.study.theater.Theater;

import java.time.LocalDateTime;

import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.temporal.TemporalAdjusters.lastInMonth;

// 매달 마지막주 수요일은 문화가 있는날. 기존 표값에서 50%를 할인한다.
public class CultureDayPriceRule implements PriceRule {
    @Override
    public Money evaluate(Money money, Theater theater, Movie movie, MovieType movieType, LocalDateTime showingTime) {
        return null;
    }

    Money evaluate(Money money, LocalDateTime startTime) {
        int lastWeekWednesday = startTime.with(lastInMonth(WEDNESDAY)).getDayOfMonth();

        if (startTime.getDayOfMonth() == lastWeekWednesday) {
            return Money.of(money.getValue() / 2);
        } else {
            return money;
        }
    }
}
