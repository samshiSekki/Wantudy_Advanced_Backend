package com.example.wantudy.study.dto;

import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.domain.StudyStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class StudyDetailResponseDto {

    private Long studyId;

    private String studyName;
    private String description;
    private String level;
    private String format;
    private String location;
    private String period;
    private String fixedStudySchedule;

    private Number peopleNum;
    private Number currentNum;
    private Number likeNum;

    private Date deadline;
    private LocalDateTime createAt;

    private StudyStatus studyStatus;

    private List<String> categories;
    private List<String> desiredTime;
    private List<String> requiredInfo;

    //리스트는 따로 받아오기
    public static StudyDetailResponseDto from(Study study){
        return StudyDetailResponseDto.builder()
                .studyId(study.getStudyId())
                .studyName(study.getStudyName())
                .description(study.getDescription())
                .level(study.getLevel())
                .format(study.getFormat())
                .location(study.getLocation())
                .period(study.getPeriod())
                .fixedStudySchedule(study.getFixedStudySchedule())
                .peopleNum(study.getPeopleNum())
                .currentNum(study.getCurrentNum())
                .likeNum(study.getLikeNum())
                .deadline(study.getDeadline())
                .createAt(study.getCreateAt())
                .studyStatus(study.getStudyStatus())
                .build();
    }

}
