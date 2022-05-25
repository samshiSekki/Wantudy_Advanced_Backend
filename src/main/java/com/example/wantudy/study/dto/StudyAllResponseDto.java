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
public class StudyAllResponseDto {

    private Long studyId;

    private String studyName;
    private String level;
    private String format;
    private String location;
    private String period;

    private Integer peopleNum;
    private Integer currentNum;
    private Integer remainNum;
    private Integer likeNum;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate deadline;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    private StudyStatus studyStatus;

    //전체 페이지에서 조회할 때는 희망시간, 필수요청 정보는 안 나오니까 카테고리만 추가해줌
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
                    .remainNum(study.getRemainNum())
                    .likeNum(study.getLikeNum())
                    .deadline(study.getDeadline())
                    .createAt(study.getCreateAt())
                    .studyStatus(study.getStudyStatus())
                    .build();
        }

//    public StudyAllResponseDto(Study study) {
//        this.studyId = study.getStudyId();
//        this.studyName = study.getStudyName();
//        this.level = study.getLevel();
//        this.format = study.getFormat();
//        this.location = study.getLocation();
//        this.period = study.getPeriod();
//        this.peopleNum = study.getPeopleNum();
//        this.currentNum = study.getCurrentNum();
//        this.remainNum = study.getRemainNum();
//        this.likeNum = study.getLikeNum();
//        this.deadline = study.getDeadline();
//        this.createAt = study.getCreateAt();
//        this.studyStatus = study.getStudyStatus();
//        this.categories = study.getCategories().stream()
//                .map(studyCategory -> studyCategory.getCategory().getCategoryName())
//                .collect(Collectors.toList());
//    }
}
