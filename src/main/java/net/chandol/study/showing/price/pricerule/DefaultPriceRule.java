package net.chandol.study.showing.price.pricerule;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.showing.price.PriceRule;
import net.chandol.study.theater.Theater;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.time.DayOfWeek.*;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;
import static net.chandol.study.movie.MovieType.*;
import static net.chandol.study.showing.price.pricerule.DefaultPriceRule.WeekTimePrice.Supporter.*;

public class DefaultPriceRule implements PriceRule {
    @Override
    public Money evaluate(Money money, Theater theater, Movie movie, MovieType movieType, LocalDateTime showingTime) {
        return evaluate(showingTime, movieType);
    }

    Money evaluate(LocalDateTime startTime, MovieType type) {
        return WeekTimePrice.findPrice(type, startTime);
    }


    enum WeekTimePrice {
        WEEKDAY_MORNING(t -> isWeekDayAndHourBetween(t, 4, 10),
                toPriceMap(
                        entry(_2D, 6000), entry(_3D, 8000),
                        entry(_4DX, 10000), entry(IMAX, 10000)
                )),
        WEEKDAY_NORMAL(
                t -> isWeekDayAndHourBetween(t, 10, 22),
                toPriceMap(
                        entry(_2D, 8000), entry(_3D, 10000),
                        entry(_4DX, 12000), entry(IMAX, 12000)
                )),
        WEEKDAY_NIGHT(
                t -> isWeekDayAndHourBetween(t.minusHours(4), 22 - 4, 28 - 4),
                toPriceMap(
                        entry(_2D, 7000), entry(_3D, 9000),
                        entry(_4DX, 11000), entry(IMAX, 11000)
                )),
        WEEKEND_MORNING(
                t -> isWeekEndAndHourBetween(t, 4, 9),
                toPriceMap(
                        entry(_2D, 7000), entry(_3D, 9000),
                        entry(_4DX, 11000), entry(IMAX, 11000)
                )),
        WEEKEND_NORMAL(
                t -> isWeekEndAndHourBetween(t, 9, 17),
                toPriceMap(
                        entry(_2D, 9000), entry(_3D, 11000),
                        entry(_4DX, 12000), entry(IMAX, 12000)
                )),
        WEEKEND_PRIME(
                t -> isWeekEndAndHourBetween(t, 17, 23),
                toPriceMap(
                        entry(_2D, 10000), entry(_3D, 12000),
                        entry(_4DX, 14000), entry(IMAX, 14000)
                )),
        WEEKEND_NIGHT(
                t -> isWeekEndAndHourBetween(t.minusHours(4), 23 - 4, 28 - 4),
                toPriceMap(
                        entry(_2D, 8000), entry(_3D, 10000),
                        entry(_4DX, 11000), entry(IMAX, 11000)
                ));

        private Predicate<LocalDateTime> predicator;
        private Map<MovieType, Money> priceMap;
        WeekTimePrice(Predicate<LocalDateTime> predicator, Map<MovieType, Money> priceMap) {
            this.predicator = predicator;
            this.priceMap = priceMap;
        }

        //=====================================================================================================//
        // WeekTime을 찾습니다.
        public static Money findPrice(MovieType movieType, LocalDateTime dateTime) {
            WeekTimePrice weekTimePrice = stream(values()).filter(x -> x.predicator.test(dateTime)).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("구간을 찾지 못하였습니다. : " + dateTime));

            return weekTimePrice.priceMap.get(movieType);
        }

        // WeekTime Helper
        static final class Supporter {
            private static final List<DayOfWeek> weekDays = Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY);
            private static final List<DayOfWeek> weekEnds = Arrays.asList(FRIDAY, SATURDAY, SUNDAY);

            static boolean isWeekDayAndHourBetween(LocalDateTime dateTime, int startHour, int endHour) {
                return weekDays.contains(dateTime.getDayOfWeek()) && isHourBetween(dateTime, startHour, endHour);
            }

            static boolean isWeekEndAndHourBetween(LocalDateTime dateTime, int startHour, int endHour) {
                return weekEnds.contains(dateTime.getDayOfWeek()) && isHourBetween(dateTime, startHour, endHour);
            }

            private static boolean isHourBetween(LocalDateTime dateTime, int startHour, int endHour) {
                int hour = dateTime.getHour();
                return hour >= startHour && hour < endHour;
            }

            static Map<MovieType, Money> toPriceMap(Map.Entry<MovieType, Money>... entries) {
                return stream(entries).collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue)
                );
            }

            static Map.Entry<MovieType, Money> entry(MovieType movieType, int price) {
                return new SimpleEntry<>(movieType, Money.of(price));
            }
        }
    }
}


