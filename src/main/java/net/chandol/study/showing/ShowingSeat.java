package net.chandol.study.showing;

import net.chandol.study.theater.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

@Entity
public class ShowingSeat {
    public enum State {RESERVED, VACANCY}

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Showing showing;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "row", column = @Column(name = "SEAT_ROW")),
            @AttributeOverride(name = "column", column = @Column(name = "SEAT_COLUMN"))
    })
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private State state;

    public ShowingSeat(Showing showing, Seat seat) {
        setShowing(showing);
        this.seat = seat;
        this.state = State.VACANCY;
    }

    private void setShowing(Showing showing) {
        this.showing = showing;
        showing.addShowingSeat(this);
    }
}

interface ShowingSeatRepository extends JpaRepository<ShowingSeat, Long> {
}