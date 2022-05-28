package com.example.wantudy.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Attendance {
    ATTENDING("재학"),
    TIMEOFF("휴학"),
    GRADUATION("졸업");

    private String attendance;
}
