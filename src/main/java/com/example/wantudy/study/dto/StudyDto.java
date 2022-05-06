package com.example.wantudy.study.dto;

import com.example.wantudy.study.domain.*;
import com.example.wantudy.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class StudyDto {

    private Long studyId;
    private User user;
    private List<StudyCategory> categories;
    private List<StudyDesiredTime> desiredTime;
    private List<StudyRequiredInfo> requiredInfo;
    private List<StudyFile> studyFiles;
    private List<String> fixedStudySchedule;
    private String studyName;
    private String description;
    private String level;
    private String format;
    private String location;
    private Number peopleNum;
    private Number currentNum;
    private String deadline;
    private String period;
    private Boolean studyStartStatus;
    private Number likeNum;
    private Date createAt;

//    public StudyDto(Long studyId, User user, List<StudyCategory> categories, List<StudyDesiredTime> desiredTime, List<StudyRequiredInfo> requiredInfo, List<StudyFile> studyFiles, List<String> fixedStudySchedule, String studyName, String description, String level, String format, String location, Number peopleNum, Number currentNum, String deadline, String period, Boolean studyStartStatus, Number likeNum, Date createAt) {
//        this.studyId = studyId;
//        this.user = user;
//        this.categories = categories;
//        this.desiredTime = desiredTime;
//        this.requiredInfo = requiredInfo;
//        this.studyFiles = studyFiles;
//        this.fixedStudySchedule = fixedStudySchedule;
//        this.studyName = studyName;
//        this.description = description;
//        this.level = level;
//        this.format = format;
//        this.location = location;
//        this.peopleNum = peopleNum;
//        this.currentNum = currentNum;
//        this.deadline = deadline;
//        this.period = period;
//        this.studyStartStatus = studyStartStatus;
//        this.likeNum = likeNum;
//        this.createAt = createAt;
//    }
}
