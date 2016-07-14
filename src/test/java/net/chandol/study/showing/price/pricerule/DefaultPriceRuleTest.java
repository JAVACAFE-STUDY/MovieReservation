package net.chandol.study.showing.price.pricerule;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.MovieType;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static java.time.DayOfWeek.*;
import static java.time.temporal.ChronoUnit.HOURS;
import static net.chandol.study.movie.MovieType.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DefaultPriceRuleTest {

    @Test
    public void evaluate() throws Exception {
        assertThat(eval(_2D, MONDAY, hour(2))).isEqualTo(8000);
        assertThat(eval(_2D, MONDAY, hour(4))).isEqualTo(6000);
        assertThat(eval(_2D, MONDAY, hour(18))).isEqualTo(8000);
        assertThat(eval(_2D, MONDAY, hour(23))).isEqualTo(7000);

        assertThat(eval(IMAX, MONDAY,  hour(5))).isEqualTo(10000);
        assertThat(eval(_4DX, MONDAY,  hour(1))).isEqualTo(11000);
        assertThat(eval(_3D, FRIDAY,   hour(3))).isEqualTo(9000);
        assertThat(eval(_2D, SATURDAY, hour(19))).isEqualTo(10000);
        assertThat(eval(_3D, SUNDAY,   hour(11))).isEqualTo(11000);
    }

    private Integer eval(MovieType movieType, DayOfWeek week, int hour){
        //given
        DefaultPriceRule rule = new DefaultPriceRule();
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(HOURS).withHour(hour).with(week);
        //when
        Money money = rule.evaluate(dateTime, movieType);
        //then
        return money.getValue();
    }

    private static int hour(int hour){
        return hour;
    }
}