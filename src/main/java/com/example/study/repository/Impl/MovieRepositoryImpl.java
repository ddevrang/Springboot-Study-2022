package com.example.study.repository.Impl;

import com.example.study.config.NaverApiConfig;
import com.example.study.model.Movie;
import com.example.study.repository.MovieRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private final RestTemplate restTemplate;
    private final NaverApiConfig naverApiConfig;

    @Override
    public List<Movie> findByTitle(String title) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", naverApiConfig.getClientId());
        httpHeaders.set("X-Naver-Client-Secret", naverApiConfig.getClientSecret());

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        URI targetUrl = UriComponentsBuilder
                .fromUriString(naverApiConfig.getMovieUrl()) //기본 url
                .queryParam("query", title) //인자
                .build()
                .encode(StandardCharsets.UTF_8) //인코딩
                .toUri();

        return restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, ResponseMovie.class)
                .getBody().getItems().stream()
                .map(m -> Movie.builder()
                        .title(m.getTitle())
                        .userRating(m.userRating)
                        .image(m.image)
                        .subtitle(m.subtitle)
                        .director(m.director)
                        .pubDate(m.pubDate)
                        .build())
                .collect(Collectors.toList());
    }

    @Data
    static class ResponseMovie implements Serializable {

        private List<Item> items;

        @Data
        public static class Item {
            String title;
            Float userRating;
            private String image;
            private String subtitle;
            private String director;
            private int pubDate;
        }
    }
}
