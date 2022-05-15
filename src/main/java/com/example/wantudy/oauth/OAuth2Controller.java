package com.example.wantudy.oauth;

import com.example.wantudy.jwt.AuthResponse;
import com.example.wantudy.common.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class OAuth2Controller {
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/oauth/callback/kakao")
    public ResponseEntity<ResponseMessage> oauth2AuthorizationKakao(@RequestParam("code") String code) throws JsonProcessingException {
        AuthResponse authResponse = userDetailsService.oauth2AuthorizationKakao(code);
        return new ResponseEntity<>(ResponseMessage.withData(200, "로그인 성공", authResponse), HttpStatus.OK);
    }

//    @GetMapping("/login/oauth2/code/google")
//    public void oauth2AuthorizationGoogle(@RequestParam("code") String code) throws JsonProcessingException {
//        AuthResponse authResponse = userDetailsService.oauth2AuthorizationGoogle(code);
//    }


}
