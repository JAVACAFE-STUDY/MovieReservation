package net.chandol.study.common;

import net.chandol.study.common.money.Money;
import org.junit.Assert;
import org.junit.Test;

import static net.chandol.study.common.MoneyMatcher.moneyIs;

public class MoneyMatcherTest {
    @Test
    public void test(){
        Money money1 = Money.of(3000);
        Assert.assertThat(money1, moneyIs(6000));
    }
}