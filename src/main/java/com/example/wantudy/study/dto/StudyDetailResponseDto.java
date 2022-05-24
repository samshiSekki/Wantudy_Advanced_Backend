package com.example.wantudy.study.dto;

import com.example.wantudy.study.Study;
import com.example.wantudy.study.domain.StudyStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private Integer peopleNum;
    private Integer currentNum;
    private Integer remainNum;
    private Integer likeNum;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate deadline;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    private StudyStatus studyStatus;

    private List<String> categories;

    private List<String> desiredTime;
    private List<String> requiredInfo;
    private List<StudyFileDto> studyFiles;

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
                .remainNum(study.getRemainNum())
                .likeNum(study.getLikeNum())
                .deadline(study.getDeadline())
                .createAt(study.getCreateAt())
                .studyStatus(study.getStudyStatus())
                .build();
    }

}
