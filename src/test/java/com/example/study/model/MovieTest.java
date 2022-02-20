package com.example.study.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieTest {

    @Test
    @DisplayName("제작년도가 올해인 영화인지 확인_거짓")
    void isThisYearMovie_False() {
        //given
        boolean expectedBoolean = false;
        Movie movie = Movie.builder()
                .title("<b>아이언맨</b>")
                .userRating(8.93f)
                .image("https://ssl.pstatic.net/imgmovie/mdi/mit110/0448/D4885-01.jpg")
                .subtitle("Iron Man")
                .director("존 파브로|")
                .pubDate(2008)
                .build();

        //when
        boolean isThisYearMovie = movie.isThisYearMovie(movie.getPubDate());

        //then
        assertEquals(expectedBoolean, isThisYearMovie);
    }

}