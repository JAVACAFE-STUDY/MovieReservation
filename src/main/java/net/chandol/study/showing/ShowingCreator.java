package net.chandol.study.showing;

import net.chandol.study.common.money.Money;
import net.chandol.study.showing.dto.ShowingCreateRequest;
import net.chandol.study.showing.price.PriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowingCreator {
    @Autowired PriceCalculator calculator;

    @Autowired ShowingRepository showingRepository;
    @Autowired ShowingSeatRepository seatRepository;

    // JPA와 연동되는 부분이라서 깔끔하게 만들긴 쉽지가 않음. ㅠㅠㅠ
    // 요건 어떻게 풀어나가야 될까요??
    public Showing create(ShowingCreateRequest showingCreateRequest) {
        Money price = calculator.calculate(showingCreateRequest.getTheater(), showingCreateRequest.getMovie(), showingCreateRequest.getMovieType(), showingCreateRequest.getStartTime());

        Showing showing = new Showing(showingCreateRequest.getTheater(), showingCreateRequest.getMovie(), showingCreateRequest.getMovieType(), showingCreateRequest.getStartTime(), price);

        // 상영을 우선 저장해야 함.
        showingRepository.save(showing);

        // 상영 좌석 생성.
        List<ShowingSeat> showingSeats = showingCreateRequest.getTheater().getSeats().stream()
                .map(seat -> new ShowingSeat(showing, seat))
                .collect(Collectors.toList());

        // 상영 좌석을 최종적으로 저장.
        seatRepository.save(showingSeats);

        return showing;
    }
}
