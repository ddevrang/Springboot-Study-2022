package com.example.study.service;

import com.example.study.dto.MovieResponseDto;
import com.example.study.exception.EmptyDataException;
import com.example.study.model.Movie;
import com.example.study.repository.Impl.MovieRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private MovieService movieService;

    @Mock
    private MovieRepositoryImpl movieRepository;

    @Test
    @DisplayName("평점이 높은 순서로 정렬이 되는지 테스트")
    void arranged_well_in_user_ratings() {

        //given
        float expectedUserRanking = 9.9f;
        Mockito.when(movieRepository.findByTitle(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(movieRepository);

        //when
        List<MovieResponseDto> actualList = movieService.search("테스트");

        //then
        assertEquals(expectedUserRanking, actualList.stream().findFirst().get().getUserRating());

    }

    @Test
    @DisplayName("평점이 0인 데이터는 제외하는지 테스트")
    void user_ratings_exclude_zero() {

        //given
        int expectedMovieSize = 4;
        Mockito.when(movieRepository.findByTitle(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(movieRepository);

        //when
        List<MovieResponseDto> actualList = movieService.search("테스트");

        //then
        assertEquals(expectedMovieSize, actualList.size());

    }

    @Test
    @DisplayName("영화 검색 조건에 해당하는 데이터가 전혀 없을 때, 예외처리가 발생하는지")
    void empty_data_exception_check() {

        //given
        Mockito.when(movieRepository.findByTitle(any())).thenReturn(Collections.emptyList());
        movieService = new MovieService(movieRepository);

        //when, then
        assertThrows(EmptyDataException.class, () -> {
            movieService.search("테스트");
        });
    }


    private List<Movie> getStubMovieList() {

        return Arrays.asList(
                Movie.builder().title("스파이더맨").userRating(9.0f).build(),
                Movie.builder().title("아이언맨").userRating(9.9f).build(),
                Movie.builder().title("앤트맨").userRating(8.5f).build(),
                Movie.builder().title("어벤져스").userRating(0.0f).build(),
                Movie.builder().title("닥터스트레인지").userRating(7.5f).build(),
                Movie.builder().title("헐크").userRating(0.0f).build()
        );
    }

}
