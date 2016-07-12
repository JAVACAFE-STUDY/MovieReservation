package net.chandol.study.showing.price.pricerule;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.showing.price.PriceRule;
import net.chandol.study.theater.Theater;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.time.DayOfWeek.*;
import static java.util.Arrays.stream;
import static net.chandol.study.movie.MovieType.*;
import static net.chandol.study.showing.price.pricerule.DefaultPriceRule.WeekTime.Supporter.isWeekDayAndHourBetween;
import static net.chandol.study.showing.price.pricerule.DefaultPriceRule.WeekTime.Supporter.isWeekEndAndHourBetween;
import static net.chandol.study.showing.price.pricerule.DefaultPriceRule.WeekTime.*;

public class DefaultPriceRule implements PriceRule {

    @Override
    public Money evaluate(Money money, Theater theater, Movie movie, MovieType movieType, LocalDateTime showingTime) {
        return evaluate(showingTime, movieType);
    }

    // 결과를 반환합니다.
    Money evaluate(LocalDateTime startTime, MovieType type) {
        WeekTime weekTime = WeekTime.getWeekTime(startTime);
        Integer price = priceMap.get(weekTime).get(type);

        return Money.of(price);
    }

    // 가격정보가 설정됩니다.
    private static final Map<WeekTime, Map<MovieType, Integer>> priceMap = new HashMap<>();
    {
        priceMap.put(WEEKDAY_MORNING, new HashMap<MovieType, Integer>() {{
            put(_2D, 6000);
            put(_3D, 8000);
            put(_4DX, 10000);
            put(IMAX, 10000);
        }});

        priceMap.put(WEEKDAY_NORMAL, new HashMap<MovieType, Integer>() {{
            put(_2D, 8000);
            put(_3D, 10000);
            put(_4DX, 12000);
            put(IMAX, 12000);
        }});

        priceMap.put(WEEKDAY_NIGHT, new HashMap<MovieType, Integer>() {{
            put(_2D, 7000);
            put(_3D, 9000);
            put(_4DX, 11000);
            put(IMAX, 11000);
        }});

        priceMap.put(WEEKEND_MORNING, new HashMap<MovieType, Integer>() {{
            put(_2D, 7000);
            put(_3D, 9000);
            put(_4DX, 11000);
            put(IMAX, 11000);
        }});

        priceMap.put(WEEKEND_NORMAL, new HashMap<MovieType, Integer>() {{
            put(_2D, 9000);
            put(_3D, 11000);
            put(_4DX, 12000);
            put(IMAX, 12000);
        }});

        priceMap.put(WEEKEND_PRIME, new HashMap<MovieType, Integer>() {{
            put(_2D, 10000);
            put(_3D, 12000);
            put(_4DX, 14000);
            put(IMAX, 14000);
        }});

        priceMap.put(WEEKEND_NIGHT, new HashMap<MovieType, Integer>() {{
            put(_2D, 8000);
            put(_3D, 10000);
            put(_4DX, 11000);
            put(IMAX, 11000);
        }});
    }


    enum WeekTime {
        WEEKDAY_MORNING (t -> isWeekDayAndHourBetween(t, 4, 10)),

        WEEKDAY_NORMAL  (t -> isWeekDayAndHourBetween(t, 10, 22)),

        WEEKDAY_NIGHT   (t -> isWeekDayAndHourBetween(t.minusHours(4), 22 - 4, 28 - 4)),

        WEEKEND_MORNING (t -> isWeekEndAndHourBetween(t, 4, 9)),

        WEEKEND_NORMAL  (t -> isWeekEndAndHourBetween(t, 9, 17)),

        WEEKEND_PRIME   (t -> isWeekEndAndHourBetween(t, 17, 23)),

        WEEKEND_NIGHT   (t -> isWeekEndAndHourBetween(t.minusHours(4), 23 - 4, 28 - 4));

        private Predicate<LocalDateTime> predicator;
        WeekTime(Predicate<LocalDateTime> predicator) {this.predicator = predicator;}

        //=====================================================================================================//
        public static WeekTime getWeekTime(LocalDateTime dateTime) {
            return stream(values()).filter(x -> x.predicator.test(dateTime)).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("구간을 찾지 못하였습니다. : " + dateTime));
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
        }

    }
}


