package com.example.study.repository;

import com.example.study.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByTitle(String title);
}
