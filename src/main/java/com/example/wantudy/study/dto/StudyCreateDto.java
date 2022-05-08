package com.example.wantudy.study.dto;

import com.example.wantudy.study.domain.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StudyCreateDto {

    private List<String> categories = new ArrayList<>();
//    private String categories;
//    private List<StudyCategory> categories = new ArrayList<>();
    private String studyName;

//    public Study toEntity() {
//        return Study.builder()
//                .studyName(studyName)
//                .build();
//    }
}
