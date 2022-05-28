package com.example.wantudy.application;

import com.example.wantudy.application.domain.Application;
import com.example.wantudy.application.dto.ApplicationCreateDto;
import com.example.wantudy.common.ResponseMessage;
import com.example.wantudy.oauth.User;
import com.example.wantudy.oauth.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Api(tags={"스터디 지원서 API"})
@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final UserDetailsServiceImpl userService;
    private final ApplicationService applicationService;

    @ApiOperation(value = "지원서 작성")
    @ApiResponses({
            @ApiResponse(code = 201, message = "지원서 작성 완료"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("") // 지원서 작성
    public ResponseEntity<ResponseMessage> createApplication(@RequestBody ApplicationCreateDto applicationCreateDto, Principal principal) {
//        String email = principal.getName();
//        User user = userService.findByEmail(email);
//        if(user == null)
//            return new ResponseEntity<>(new ResponseMessage(404, "존재하지 않는 유저"), HttpStatus.NOT_FOUND);
        User user = userService.findByEmail("test@naver.com");
        Application application = applicationService.saveApplication(user, applicationCreateDto);

        return new ResponseEntity<>(new ResponseMessage(201, "지원서 작성 완료", application), HttpStatus.CREATED);
    }

    @PatchMapping("/{applicationId}") // 지원서 수정
    public ResponseEntity<ResponseMessage> updateApplication(@PathVariable("applicationId") Long applicationId, @RequestBody ApplicationCreateDto applicationUpdateDto) {
        //        String email = principal.getName();
//        User user = userService.findByEmail(email);
//        if(user == null)
//            return new ResponseEntity<>(new ResponseMessage(404, "존재하지 않는 유저"), HttpStatus.NOT_FOUND);
        User user = userService.findByEmail("test@naver.com");

        Application application = applicationService.findByApplicationId(applicationId);
        if(application == null)
            return new ResponseEntity<>(new ResponseMessage(404, "존재하지 않는 지원서"), HttpStatus.NOT_FOUND);
        applicationService.updateApplication(application, applicationUpdateDto);

        return new ResponseEntity<>(new ResponseMessage(200, "강의 리뷰 수정 성공"), HttpStatus.OK);

    }

    @DeleteMapping("/{applicationId}") // 지원서 삭제
    public ResponseEntity<ResponseMessage> deleteApplication(@PathVariable("applicationId") Long applicationId, Principal principal) {
//        String email = principal.getName();
//        User user = userDetailsService.findUserByEmail(email);

        Application application = applicationService.findByApplicationId(applicationId);
        if(application == null)
            return new ResponseEntity<>(new ResponseMessage(404, "존재하지 않는 지원서"), HttpStatus.NOT_FOUND);
        applicationService.deleteApplication(application);
        return new ResponseEntity<>(new ResponseMessage(200, "지원서 삭제 완료"), HttpStatus.OK);
    }
}
