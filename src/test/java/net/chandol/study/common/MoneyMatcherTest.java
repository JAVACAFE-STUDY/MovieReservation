package net.chandol.study.common;

import net.chandol.study.common.money.Money;
import org.junit.Assert;
import org.junit.Test;

import static net.chandol.study.common.MoneyMatcher.moneyIs;
import static org.hamcrest.CoreMatchers.not;

public class MoneyMatcherTest {
    @Test
    public void test1(){
        Money money1 = Money.of(3000);
        Assert.assertThat(money1, moneyIs(3000));
    }

    @Test
    public void test2(){
        Money money1 = Money.of(3000);
        Assert.assertThat(money1, not(moneyIs(7000)));
    }
}