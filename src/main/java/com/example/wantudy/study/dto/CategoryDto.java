package com.example.wantudy.study.dto;

import com.example.wantudy.study.domain.Category;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private long categoryId;
    private String categoryName;
    private List<CategoryDto> child;

    public CategoryDto(final Category category){
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.child = category.getChild().stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    public Category toEntity() {
        return Category.builder()
                .categoryName(categoryName)
                .build();
    }
}
