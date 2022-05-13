package com.example.wantudy.study.dto;

import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.domain.StudyStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class StudyAllResponseDto {

    private Long studyId;

    private String studyName;
    private String level;
    private String format;
    private String location;
    private String period;

    private Number peopleNum;
    private Number currentNum;
    private Number likeNum;

    private LocalDate deadline;
    private LocalDateTime createAt;

    private StudyStatus studyStatus;

    private List<String> categories;

    //리스트는 따로 받아오기
    public static StudyAllResponseDto from(Study study){
            return StudyAllResponseDto.builder()
                    .studyId(study.getStudyId())
                    .studyName(study.getStudyName())
                    .level(study.getLevel())
                    .format(study.getFormat())
                    .location(study.getLocation())
                    .period(study.getPeriod())
                    .peopleNum(study.getPeopleNum())
                    .currentNum(study.getCurrentNum())
                    .likeNum(study.getLikeNum())
                    .deadline(study.getDeadline())
                    .createAt(study.getCreateAt())
                    .studyStatus(study.getStudyStatus())
                    .build();
        }


}
