package net.chandol.study.common;

import net.chandol.study.common.money.Money;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MoneyMatcher extends TypeSafeMatcher<Money> {
    private final Integer moneyValue;

    MoneyMatcher(Integer moneyValue) {
        this.moneyValue = moneyValue;
    }

    @Override
    protected boolean matchesSafely(Money money) {
        return this.moneyValue == money.getValue();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("expected ")
                .appendValue(Money.of(moneyValue))
                .appendText(" ");
    }

    @Factory
    public static Matcher<Money> moneyIs(final Integer money) {
        return new MoneyMatcher(money);
    }


}

