package com.example.study.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Movie {
    private String title;
    private float userRating;
}
