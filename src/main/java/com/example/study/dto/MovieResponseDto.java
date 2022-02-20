package com.example.study.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieResponseDto {
    private String title;
    private float userRating;
    private String image;
    private String subtitle;
    private String director;
    private int pubDate;
}