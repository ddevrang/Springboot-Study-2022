package com.example.study.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("naver.api")
@Getter
@Setter
public class NaverApiConfig {
    private String movieUrl;
    private String clientId;
    private String clientSecret;
}
