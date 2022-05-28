package com.example.wantudy.application.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private String gender;
}
//    public static Gender nameOf(String name) {
//        for (Gender status : Gender.values()) {
//            if (status.getGender().equals(name)) {
//                return status;
//            }
//        }
//        return null;
//    }
