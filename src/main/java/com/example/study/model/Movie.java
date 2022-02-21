package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String title;
    private float userRating;
    private String image;
    private String subtitle;
    private String director;
    private int pubDate;

    public boolean isThisYearMovie() {

        int thisYear = LocalDate.now().getYear();

        return pubDate == thisYear;
    }
}