package net.chandol.study.common.money;

import lombok.Value;

@Value
public class Money {
    private int value;

    public Money add(int value) {
        return new Money(this.value + value);
    }

    public Money add(Money money) {
        return this.add(money.getValue());
    }

    public Money multiply(int value) {
        return new Money(this.value * value);
    }

    public Money multiply(Money money) {
        return new Money(this.value * money.getValue());
    }
}
