package net.chandol.study.movie.helper;

import net.chandol.study.movie.MovieType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MovieTypeConverter implements AttributeConverter<MovieType, String> {
    @Override
    public String convertToDatabaseColumn(MovieType movieType) {
        return movieType.name();
    }

    @Override
    public MovieType convertToEntityAttribute(String movieType) {
        return MovieType.getMovieType(movieType);
    }
}
