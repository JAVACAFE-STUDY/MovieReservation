package net.chandol.study.showing.price.pricerule;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.MovieType;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static java.time.DayOfWeek.*;
import static java.time.temporal.ChronoUnit.HOURS;
import static net.chandol.study.common.MoneyMatcher.moneyIs;
import static net.chandol.study.movie.MovieType.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultPriceRuleTest {

    @Test
    public void evaluate() throws Exception {
        ruleEvaluate(_2D, MONDAY, 2, 8000);
        ruleEvaluate(_2D, MONDAY, 4, 6000);
        ruleEvaluate(_2D, MONDAY, 18, 8000);
        ruleEvaluate(_2D, MONDAY, 23, 7000);

        ruleEvaluate(IMAX, MONDAY, 5, 10000);
        ruleEvaluate(_4DX, MONDAY, 1, 11000);
        ruleEvaluate(_3D, FRIDAY, 3, 9000);
        ruleEvaluate(_2D, SATURDAY, 19, 10000);
        ruleEvaluate(_3D, SUNDAY, 11, 11000);
    }

    private void ruleEvaluate(MovieType movieType, DayOfWeek week, int hour, int expected){
        //given
        DefaultPriceRule rule = new DefaultPriceRule();
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(HOURS).withHour(hour).with(week);
        //when
        Money money = rule.evaluate(dateTime, movieType);
        //then
        assertThat(money, moneyIs(expected));
    }
}