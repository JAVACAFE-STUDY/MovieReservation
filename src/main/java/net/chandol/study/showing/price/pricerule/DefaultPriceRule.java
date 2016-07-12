package net.chandol.study.showing.price.pricerule;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.showing.price.PriceRule;
import net.chandol.study.theater.Theater;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.time.DayOfWeek.*;
import static java.util.Arrays.asList;
import static net.chandol.study.showing.price.pricerule.DefaultPriceRule.WeekTimePrice.findPrice;

public class DefaultPriceRule implements PriceRule {
    @Override
    public Money evaluate(Money money, Theater theater, Movie movie, MovieType movieType, LocalDateTime showingTime) {
        return evaluate(showingTime, movieType);
    }

    Money evaluate(LocalDateTime startTime, MovieType type) {
        return Money.of(findPrice(startTime, type));
    }

    enum WeekTimePrice {
        WEEKDAY_MORNING(6000, 8000, 10000, 10000),
        WEEKDAY_NORMAL(8000, 10000, 12000, 12000),
        WEEKDAY_NIGHT(7000, 9000, 11000, 11000),
        WEEKEND_MORNING(7000, 9000, 11000, 11000),
        WEEKEND_NORMAL(9000, 11000, 12000, 12000),
        WEEKEND_PRIME(10000, 12000, 14000, 14000),
        WEEKEND_NIGHT(7000, 9000, 11000, 11000);

        private int _2d, _3d, _4dx, imax;

        WeekTimePrice(int _2d, int _3d, int _4dx, int imax) {
            this._2d = _2d;
            this._3d = _3d;
            this._4dx = _4dx;
            this.imax = imax;
        }

        public static int findPrice(LocalDateTime dateTime, MovieType type) {
            WeekTimePrice weekTime = WeekTimeFinder.findWeekTime(dateTime);
            switch (type) {
                case _2D:
                    return weekTime._2d;
                case _3D:
                    return weekTime._3d;
                case _4DX:
                    return weekTime._4dx;
                case IMAX:
                    return weekTime.imax;
                default:
                    throw new IllegalArgumentException("금액을 찾지 못함");
            }
        }

        private static class WeekTimeFinder {
            private static final Set<DayOfWeek> WEEKDAY
                    = new HashSet<>(asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY));

            private static WeekTimePrice findWeekTime(LocalDateTime time) {
                DayOfWeek dayOfWeek = time.getDayOfWeek();
                int hour = time.getHour();

                //월요일 0-4는 주말심야
                if (MONDAY == dayOfWeek && between(hour, 0, 4)) {
                    return WEEKEND_NIGHT;
                }
                //금요일 0-4는 평일심야
                else if (FRIDAY == dayOfWeek && between(hour, 0, 4)) {
                    return WEEKDAY_NIGHT;
                }
                // 평일 시간별
                else if (WEEKDAY.contains(dayOfWeek)) {
                    if (between(hour, 4, 10))
                        return WEEKDAY_MORNING;
                    else if (between(hour, 10, 22))
                        return WEEKDAY_NORMAL;
                    else if (between(hour, 22, 24))
                        return WEEKDAY_NIGHT;
                }
                // 주말 시간별
                else {
                    if (between(hour, 4, 9))
                        return WEEKEND_MORNING;
                    if (between(hour, 9, 18))
                        return WEEKEND_NORMAL;
                    else if (between(hour, 18, 23))
                        return WEEKEND_PRIME;
                    else if (between(hour, 22, 24))
                        return WEEKDAY_NIGHT;
                }

                throw new IllegalArgumentException("시간을 찾지못함");
            }

            private static boolean between(int target, int start, int end) {
                return target >= start && target < end;
            }
        }
    }
}

