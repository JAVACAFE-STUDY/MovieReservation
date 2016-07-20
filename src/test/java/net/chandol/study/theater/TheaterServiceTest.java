package net.chandol.study.theater;

import net.chandol.study.theater.dto.TheaterCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheaterServiceTest {
    @Autowired TheaterService theaterService;

    @Test
    public void createTheater() throws Exception {
        //given
        //when
        Theater theater = theaterService.create(new TheaterCreateRequest("서현점", 20, 10));

        //then
        assertThat(theater.getName()).isEqualTo("서현점");

        assertThat(theater.getSeats())
                .hasSize(200)
                .extracting("column", "row")
                .contains(tuple(1, 'A'), tuple(1, 'B'));
    }
}