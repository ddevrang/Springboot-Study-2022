package com.example.study.model;

import com.example.study.dto.MovieResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MovieGroup {

    private final List<Movie> movieList;

    public MovieGroup(List<Movie> movieList){
        this.movieList = movieList;
    }

    public List<Movie> sortByUserRating(){
        return movieList.stream()
                .filter(m -> m.getUserRating() != 0.0f)
                .sorted(Comparator.comparing(Movie::getUserRating, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

}
