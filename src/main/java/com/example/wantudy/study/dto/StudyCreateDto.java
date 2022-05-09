package com.example.wantudy.study.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudyCreateDto {

    private String studyName;
    private String description;
    private String level;
    private String format;
    private String location;
    private String period;
    private Number peopleNum;
    private Date deadline;
    private List<String> categories = new ArrayList<>();
    private List<String> desiredTime = new ArrayList<>();
    private List<String> requiredInfo = new ArrayList<>();
//    private List<String> studyFiles = new ArrayList<>();
}
