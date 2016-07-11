package net.chandol.study.showing;

import net.chandol.study.common.money.Money;
import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieType;
import net.chandol.study.theater.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowingCreator {
    @Autowired ShowingRepository showingRepository;
    @Autowired ShowingSeatRepository seatRepository;

    // JPA와 연동되는 부분이라서 깔끔하게 만들긴 쉽지가 않음. ㅠㅠㅠ
    // 요건 어떻게 풀어나가야 될까요??
    public Showing create(Theater theater, Movie movie, MovieType movieType, LocalDateTime showingTime, Money price) {
        Showing showing = new Showing(theater, movie, movieType, showingTime, price);

        // 상영을 우선 저장해야 함.
        showingRepository.save(showing);

        // 상영 좌석 생성.
        List<ShowingSeat> showingSeats = theater.getSeats().stream()
                .map(seat -> new ShowingSeat(showing, seat))
                .collect(Collectors.toList());

        // 상영 좌석을 최종적으로 저장.
        seatRepository.save(showingSeats);

        return showing;
    }
}
