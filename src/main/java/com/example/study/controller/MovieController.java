package com.example.study.controller;

import com.example.study.dto.MovieResponseDto;
import com.example.study.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/api/search/movies")
    public List<MovieResponseDto> movieSearch(@RequestParam("keyword") String keyword) {

        return movieService.search(keyword);
    }
}