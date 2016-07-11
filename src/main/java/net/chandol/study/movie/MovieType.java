package net.chandol.study.movie;

import java.util.Optional;

import static java.util.Arrays.stream;

public enum MovieType {
    // 자바에서 이름앞에 숫자가 앞에 나오지 못함..ㅠㅠ
    _2D("2D"), _3D("3D"), _4DX("4D"), IMAX("IMAX");

    String name;

    public static MovieType getMovieType(String movieTypeName) {
        Optional<MovieType> movieType = stream(MovieType.values())
                .filter(x -> x.name().equals(movieTypeName))
                .findAny();

        if (movieType.isPresent())
            return movieType.get();
        else
            throw new IllegalArgumentException("찾을 수 없는 영화 타입");
    }

    MovieType(String name) {
        this.name = name;
    }
}
