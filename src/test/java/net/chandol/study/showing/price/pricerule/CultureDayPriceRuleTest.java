package net.chandol.study.showing.price.pricerule;

import net.chandol.study.common.money.Money;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sejong on 2016. 7. 12..
 */
public class CultureDayPriceRuleTest {
    @Test
    public void evaluate() throws Exception {
        //given
        CultureDayPriceRule rule = new CultureDayPriceRule();

        //when
        Money cultureDayPrice = rule.evaluate(Money.of(10000), LocalDateTime.of(2016, 7, 27, 9, 26));
        Money normalPrice = rule.evaluate(Money.of(10000), LocalDateTime.of(2016, 7, 7, 9, 26));

        //then
        assertThat(cultureDayPrice).isEqualTo(Money.of(5000));
        assertThat(normalPrice).isEqualTo(Money.of(10000));
    }
}