package net.chandol.study.theater;

import net.chandol.study.theater.dto.TheaterCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        assertThat(theater.getName(), is("서현점"));
        assertThat(theater.getSeats().size(), is(200));

        List<Seat> seats = theater.getSeats();
        assertThat(seats.get(0).getColumn(), is(1));
        assertThat(seats.get(0).getRow(), is('A'));
    }
}