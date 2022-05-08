package com.example.wantudy;

import com.example.wantudy.users.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController("/login/oauth2/code")
@RequiredArgsConstructor
public class Oauth2Controller {
    private final UserService userService;

    @GetMapping("/kakao")
    public void oauth2AuthorizationKakao(@RequestParam("code") String code) throws JsonProcessingException {
        userService.oauth2AuthorizationKakao(code);
    }

    @GetMapping("/google")
    public void oauth2AuthorizationGoogle(@RequestParam("code") String code) throws JsonProcessingException {
        userService.oauth2AuthorizationKakao(code);
    }

//    @GetMapping(value="/kakao")
//    public ResponseEntity<String> kakaoOauthRedirect(@RequestParam String code) throws JsonProcessingException {
//        RestTemplate rt = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", "86d130c8c94c8dbcbb6c5756050fbaae");
//        params.add("redirect_uri", "http://localhost:8080/login/oauth2/code/kakao");
//        params.add("code", code);
//        params.add("client_secret","KO6AUr0e9jKa33rkaeB7PJRqA2VOsayh");
//
//        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(params, headers);
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoRequest,
//                String.class
//        );
//        System.out.println("response.getBody() = " + response.getBody());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(response.getBody());
//        String accessToken = jsonNode.get("access_token").asText();
//        System.out.println("accessToken = " + accessToken);
//        HttpHeaders headers1 = new HttpHeaders();
//        headers1.add("Authorization", "Bearer " + accessToken);
//        headers1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//        HttpEntity<HttpHeaders> kakaoRequest1 = new HttpEntity<>(headers1);
//        ResponseEntity<String> profileResponse = rt.exchange(
//                "https://kapi.kakao.com/v2/user/me",
//                HttpMethod.POST,
//                kakaoRequest1,
//                String.class
//        );
//        return profileResponse;
//    }
}
