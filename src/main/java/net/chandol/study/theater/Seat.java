package net.chandol.study.theater;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Tolerate;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class Seat {
    private Character row;
    private Integer column;

    @Tolerate
    protected Seat() {}
}
