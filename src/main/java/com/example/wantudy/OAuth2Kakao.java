package com.example.wantudy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@AllArgsConstructor
@Service
@Component
@PropertySource("classpath:application-oauth.properties")
public class OAuth2Kakao {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    // value 어노테이션을 통해 클래스 필드에 의존성 주입할 경우 객체가 생성될 때
//    @Value("${kakao.kakaoOAuth2ClientId}")
//    private String kakaoOAuth2ClientId;
    private final String kakaoOAuth2ClientId="86d130c8c94c8dbcbb6c5756050fbaae";

//    @Value("${redirectUri}")
//    private final String redirectUri;
    private final String redirectUri="http://localhost:8080";
//    @Value("${clientSecret}")
//    private final String clientSecret;
    private final String clientSecret="KO6AUr0e9jKa33rkaeB7PJRqA2VOsayh";


    public AuthorizationKakao callTokenApi(String code) throws JsonProcessingException {
        String grantType = "authorization_code";
        System.out.println("kakaoOAuth2ClientId = " + kakaoOAuth2ClientId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", kakaoOAuth2ClientId);
        params.add("redirect_uri", redirectUri + "/login/oauth2/code/kakao");
        params.add("code", code);
        params.add("client_secret",clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kauth.kakao.com/oauth/token";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        AuthorizationKakao authorization = objectMapper.readValue(response.getBody(), AuthorizationKakao.class);
        return authorization;

    }

    /**
     * accessToken 을 이용한 유저정보 받기
     * @return
     */
    public String callGetUserByAccessToken(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v2/user/me";

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }

}
