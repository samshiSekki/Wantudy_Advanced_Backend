package com.example.wantudy.study.dto;

import com.example.wantudy.study.domain.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class StudyCreateDto {

//    private List<StudyCategory> categories;
////    private List<StudyDesiredTime> desiredTime;
////    private List<StudyRequiredInfo> requiredInfo;
////    private List<StudyFile> studyFiles;
    private String studyName;
    private String description;
    private String level;
    private String format;
    private String location;
    private Number peopleNum;
    private Date deadline;
    private String period;


//
    @Builder
    public StudyCreateDto(List<StudyCategory> categories, List<StudyDesiredTime> desiredTime, List<StudyRequiredInfo> requiredInfo, String studyName, String description, String level, String format, String location, Number peopleNum, Date deadline, String period) {
//        this.categories = categories;
//        this.desiredTime = desiredTime;
//        this.requiredInfo = requiredInfo;
        this.studyName = studyName;
        this.description = description;
        this.level = level;
        this.format = format;
        this.location = location;
        this.peopleNum = peopleNum;
        this.deadline = deadline;
        this.period = period;
    }

    public Study toEntity() {

        return Study.builder()
                .studyName(studyName)
//                .categories(categories)
//                .desiredTime(desiredTime)
//                .requiredInfo(requiredInfo)
                .description(description)
                .level(level)
                .format(format)
                .location(location)
                .peopleNum(peopleNum)
                .deadline(deadline)
                .period(period)
                .build();
    }
}
