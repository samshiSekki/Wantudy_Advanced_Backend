package com.example.wantudy.users;

import com.example.wantudy.AuthorizationKakao;
import com.example.wantudy.OAuth2Kakao;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {
    private final OAuth2Kakao oauth2Kakao;
    // 카카오로 인증받기
    public void oauth2AuthorizationKakao(String code) throws JsonProcessingException {
        AuthorizationKakao authorization = oauth2Kakao.callTokenApi(code);
        String userInfoFromKakao = oauth2Kakao.callGetUserByAccessToken(authorization.getAccess_token());
        System.out.println("userInfoFromKakao = " + userInfoFromKakao);
    }
}
