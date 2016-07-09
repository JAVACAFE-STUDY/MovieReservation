package net.chandol.study.theater;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class Seat {
    private Character row;
    private Integer column;

    protected Seat() {
    }

    public Seat(Character row, Integer column) {
        this.row = row;
        this.column = column;
    }
}
