package net.chandol.study.common.support;

import net.chandol.study.common.money.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money money) {
        if (Objects.nonNull(money))
            return money.getValue();
        else
            return 0;
    }

    @Override
    public Money convertToEntityAttribute(Integer value) {
        if (Objects.nonNull(value))
            return Money.of(value);
        else
            return Money.of(0);
    }
}
