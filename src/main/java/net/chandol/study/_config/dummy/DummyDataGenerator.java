package net.chandol.study._config.dummy;

import net.chandol.study.movie.Movie;
import net.chandol.study.movie.MovieService;
import net.chandol.study.movie.dto.MovieCreateRequest;
import net.chandol.study.theater.Theater;
import net.chandol.study.theater.TheaterService;
import net.chandol.study.theater.dto.TheaterCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static net.chandol.study.movie.MovieType.*;

public class DummyDataGenerator {
    @Autowired MovieService movieService;
    @Autowired TheaterService theaterService;

    public Map<String, Movie> persistMovie() {
        return getMovieCreateRequests().values().stream()
                .map(x -> movieService.create(x))
                .collect(toMap(Movie::getName, identity()));
    }

    public Map<String, Theater> persistTheater() {
        return getTheaterCreateRequests().values().stream()
                .map(x -> theaterService.create(x))
                .collect(toMap(Theater::getName, identity()));
    }

    public static Map<String, MovieCreateRequest> getMovieCreateRequests() {
        Map<String, MovieCreateRequest> reqeusts = new HashMap<>();

        reqeusts.put("WALL-E", new MovieCreateRequest() {{
            setName("WALL-E");
            setDescription("텅 빈 지구에 홀로 남아 수백 년이란 시간을 외롭게 일만 하며 보내던 월-E (WALL-E: Waste Allocation Load Lifter Earth-Class, 지구 폐기물 수거-처리용 로봇). 그런 그가 매력적인 탐사 로봇 ‘이브’와 마주친 순간, 잡동사니 수집만이 낙이던 인생에도 소중한 목표가 생긴다. 이브는 지구의 미래를 결정할 열쇠가 우연히 월-E의 손에 들어간 사실을 알게 되고, 고향별로 돌아갈 날만 애타게 기다리는 인간들에게 이를 보고하기 위해 서둘러 우주로 향한다. 한편 월-E는 이브를 뒤쫓아 은하를 가로지르며, 스크린 사상 가장 짜릿한 상상이 넘치는 어드벤처를 선사한다. 이제껏 꿈에서도 볼 수 없었던 미래 세계를 배경으로 우주에서 펼쳐지는 월-E의 환상적인 모험! 애완용 바퀴벌레, 용맹스럽지만 어딘가 나사가 빠진 듯한 사회 부적응 로봇 군단 등 일련의 유쾌한 캐릭터들이 여기에 동참한다.");
            setMovieTypes(_2D, _3D);
            setRunningTime(104);
        }});

        reqeusts.put("인사이드 아웃", new MovieCreateRequest() {{
            setName("인사이드 아웃");
            setDescription("모든 사람의 머릿속에 존재하는 감정 컨트롤 본부 \n 그곳에서 불철주야 열심히 일하는 기쁨, 슬픔, 버럭, 까칠, 소심 다섯 감정들. \n 이사 후 새로운 환경에 적응해야 하는 ‘라일리’를 위해 \n 그 어느 때 보다 바쁘게 감정의 신호를 보내지만 \n 우연한 실수로 ‘기쁨’과 ‘슬픔’이 본부를 이탈하게 되자 \n '라일리’의 마음 속에 큰 변화가 찾아온다. \n '라일리'가 예전의 모습을 되찾기 위해서는 ‘기쁨’과 ‘슬픔’이 본부로 돌아가야만 한다! \n 그러나 엄청난 기억들이 저장되어 있는 머릿속 세계에서 본부까지 가는 길은 험난하기만 한데… \n 과연, ‘라일리’는 다시 행복해질 수 있을까? \n  \n 지금 당신의 머릿속에서 벌어지는 놀라운 일! \n 하루에도 몇번씩 변하는 감정의 비밀이 밝혀진다!");
            setMovieTypes(_2D, _3D);
            setRunningTime(102);
        }});

        reqeusts.put("인터스텔라", new MovieCreateRequest() {{
            setName("인터스텔라");
            setDescription("세계 각국의 정부와 경제가 완전히 붕괴된 미래가 다가온다. \n 지난 20세기에 범한 잘못이 전 세계적인 식량 부족을 불러왔고, NASA도 해체되었다. \n 이때 시공간에 불가사의한 틈이 열리고, 남은 자들에게는 이 곳을 탐험해 인류를 구해야 하는 임무가 지워진다.\n 사랑하는 가족들을 뒤로 한 채 인류라는 더 큰 가족을 위해, 그들은 이제 희망을 찾아 우주로 간다.\n 그리고 우린 답을 찾을 것이다. 늘 그랬듯이…");
            setMovieTypes(_2D, _3D, IMAX);
            setRunningTime(169);
        }});

        reqeusts.put("센과 치히로의 행방불명", new MovieCreateRequest() {{
            setName("센과 치히로의 행방불명");
            setDescription("금지된 세계의 문이 열렸다! \n\n" + " 이사 가던 날, 수상한 터널을 지나자 인간에게는 금지된 신들의 세계로 오게 된 치히로.. \n" + " 신들의 음식을 먹은 치히로의 부모님은 돼지로 변해버린다. \n" + " “걱정마, 내가 꼭 구해줄게…” \n" + "  \n" + "  \n" + " 겁에 질린 치히로에게 다가온 정체불명의 소년 하쿠. \n" + " 그의 따뜻한 말에 힘을 얻은 치히로는 인간 세계로 돌아가기 위해 사상 초유의 미션을 시작하는데…");
            setMovieTypes(_2D);
            setRunningTime(126);
        }});

        return reqeusts;
    }

    public Map<String, TheaterCreateRequest> getTheaterCreateRequests() {
        Map<String, TheaterCreateRequest> requests = new HashMap<>();

        requests.put("서현점", new TheaterCreateRequest("서현점", 20, 10));
        requests.put("강남점", new TheaterCreateRequest("강남점", 15, 20));

        return requests;
    }
}
