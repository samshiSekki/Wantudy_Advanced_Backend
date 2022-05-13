package com.example.wantudy.study.dto;

import com.example.wantudy.study.Study;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private LocalDate deadline;
    private List<String> categories = new ArrayList<>();
    private List<String> desiredTime = new ArrayList<>();
    private List<String> requiredInfo = new ArrayList<>();
//    private List<String> studyFileNames = new ArrayList<>();

    //DTO를 Entity로 변환해주기 위한 메소드
    public Study toEntity(){
        return Study.builder()
                .studyName(studyName)
                .description(description)
                .level(level)
                .format(format)
                .location(location)
                .period(period)
                .peopleNum(peopleNum)
                .deadline(deadline)
                .build();
    }
}
