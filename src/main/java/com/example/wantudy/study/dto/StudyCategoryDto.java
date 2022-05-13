package com.example.wantudy.study.dto;

import com.example.wantudy.study.domain.Category;
import com.example.wantudy.study.Study;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class StudyCategoryDto {

    private Study study;
    private Category category;

}
