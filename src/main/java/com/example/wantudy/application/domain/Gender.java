package com.example.wantudy.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private String gender;
}
