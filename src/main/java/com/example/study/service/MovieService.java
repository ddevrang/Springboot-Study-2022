package com.example.study.service;

import com.example.study.dto.MovieResponseDto;
import com.example.study.exception.EmptyDataException;
import com.example.study.exception.ErrorCode;
import com.example.study.model.Movie;
import com.example.study.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieResponseDto> search(String title) {

        List<Movie> movieList = movieRepository.findByTitle(title);

        if (movieList.isEmpty()) {
            throw new EmptyDataException(ErrorCode.NOT_FOUND);
        }

        return movieList.stream()
                .filter(m -> m.getUserRating() != 0.0f)
                .sorted(Comparator.comparing(Movie::getUserRating, Comparator.reverseOrder()))
                .map(m -> MovieResponseDto.builder()
                        .title(m.getTitle())
                        .userRating(m.getUserRating())
                        .image(m.getImage())
                        .subtitle(m.getSubtitle())
                        .director(m.getDirector())
                        .pubDate(m.getPubDate())
                        .build())
                .collect(Collectors.toList());
    }
}
