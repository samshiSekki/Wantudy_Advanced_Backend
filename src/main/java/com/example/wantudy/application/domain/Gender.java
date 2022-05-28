package com.example.wantudy.application.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import springfox.documentation.swagger2.mappers.EnumMapper;

@Getter
@RequiredArgsConstructor
public enum Gender implements EnumMapperType {
    MALE("남자"),
    FEMALE("여자");

    @Getter
    private final String title; // 설명 조회 - "남자"

    @Override
    public String getCode() { // 이름을 조회 - MALE
        return name();
    }
}
