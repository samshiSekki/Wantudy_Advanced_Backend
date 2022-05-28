package com.example.wantudy.application.dto;

import com.example.wantudy.application.domain.Application;
import com.example.wantudy.application.domain.Attendance;
import com.example.wantudy.application.domain.Gender;
import com.example.wantudy.application.domain.Keyword;
import com.example.wantudy.oauth.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel(value = "지원서 작성 DTO")
public class ApplicationCreateDto {
    @ApiModelProperty(value = "지원서명", required = false)
    private String applicationName;

    @ApiModelProperty(value = "성별", required = false, example = "MALE")
    private Gender gender;

    @ApiModelProperty(value = "나이", required = false, example = "20")
    private Integer age;

    @ApiModelProperty(value = "소속", required = false, example = "서울대학교")
    private String organization;

    @ApiModelProperty(value = "전공", required = false, example = "생명공학과")
    private String major;

    @ApiModelProperty(value = "재학 여부", required = false, example = "재학")
    private Attendance attendance;

    @ApiModelProperty(value = "학기", required = false, example = "2-1학기")
    private String semester;

    @ApiModelProperty(value = "거주지", required = false, example = "경기도 고양시 덕양구")
    private String address;

    @ApiModelProperty(value = "관심 분야", required = false, example = "['마케팅', '기획']")
    private List<String> interests = new ArrayList<>();

    @ApiModelProperty(value = "자신을 표현하는 키워드", required = false, example = "['꼼꼼한', '성실한']")
    private List<String> keywords = new ArrayList<>();

    public Application toEntity(User user){
        return Application
                .builder()
                .user(user)
                .applicationName(applicationName)
                .gender(gender)
                .age(age)
                .organization(organization)
                .major(major)
                .attendance(attendance)
                .semester(semester)
                .address(address)
                .build();
    }
}
