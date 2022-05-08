package com.example.wantudy.study.dto;

import com.example.wantudy.study.domain.Category;
import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.domain.StudyCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDto {

    private String categoryName;

    @Builder
    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category toEntity() {
        return Category.builder()
                .categoryName(categoryName)
                .build();
    }
}
