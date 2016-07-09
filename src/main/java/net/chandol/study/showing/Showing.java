package net.chandol.study.showing;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.theater.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
public class Showing {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Theater theater;
    private LocalDateTime startTime;
    private Money price;

    @OneToMany(mappedBy = "showing")
    private List<ShowingSeat> showingSeats = new ArrayList<>();

    protected Showing() {
    }

    // 에그리게이터이지만, 하위 객체가 엔티티인 경우는 어떻게 처리해야 할까??
    public Showing(Theater theater, Movie movie, LocalDateTime startTime, Money price) {
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.price = price;
    }

    void addShowingSeat(ShowingSeat seat) {
        this.showingSeats.add(seat);
    }
}

interface ShowingRepository extends JpaRepository<Showing, Long> {
}
