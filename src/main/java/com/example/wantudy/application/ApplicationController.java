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

//    @PatchMapping("/{applicationId}") // 지원서 수정
//    public ResponseEntity<ResponseMessage> updateeApplication(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewPostDto reviewUpdateDto) {
//        Review review = reviewService.findByReviewId(reviewId);
//        if(review != null) {
//            reviewService.updateReview(reviewUpdateDto, review);
//            return new ResponseEntity<>(new ResponseMessage(200, "강의 리뷰 수정 성공"), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new ResponseMessage(404, "존재하지 않는 강의 리뷰"), HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/{applicationId}") // 지원서 삭제
//    public ResponseEntity<ResponseMessage> deleteReview(@PathVariable("reviewId") Long reviewId, Principal principal) {
//        String email = principal.getName();
//        User user = userDetailsService.findUserByEmail(email);
//        Review review = reviewService.findByReviewId(reviewId);
//        if(review != null) {
//            reviewService.deleteReview(reviewId);
//            List<Review> reviews = reviewService.findAllReviewsByUser(user);
//            if(reviews.size() == 0) { // 삭제하고 나서 리뷰가 더이상 없는 경우 writeStatus 바꿔주기
//                user.updateReviewStatus();
//            }
//            return new ResponseEntity<>(new ResponseMessage(200, "강의 리뷰 삭제 성공"), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new ResponseMessage(200, "존재하지 않는 강의 리뷰"), HttpStatus.NOT_FOUND);
//    }

}
