package net.chandol.study.theater;

import net.chandol.study.theater.dto.TheaterCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    @Autowired
    TheaterRepsitory repository;

    // 영화관 정보를 생성합니다.
    // 영화관의 좌석은 우선 x,y형태로 생성하며 x는 숫자로 y는 알파벳으로 표기합니다.
    public Theater createTheater(TheaterCreateRequest theaterCreateRequest) {
        Theater theater = new Theater(theaterCreateRequest.getName(), theaterCreateRequest.getColumnSize(), theaterCreateRequest.getRowSize());
        repository.save(theater);

        return theater;
    }

    public Theater getTheater(long id){
        return repository.getOne(id);
    }
}
