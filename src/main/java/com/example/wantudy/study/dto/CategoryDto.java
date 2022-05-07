package com.example.wantudy.study.dto;

import com.example.wantudy.study.domain.Category;
import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.domain.StudyCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDto {

    private String categoryName;
    private List<StudyCategory> studies;

//
//    public CategoryDto(Category category){
//        this.categoryId = category.getCategoryId();
//        this.categoryName = category.getCategoryName();
//        this.studies = category.getStudies();
//    }
}
