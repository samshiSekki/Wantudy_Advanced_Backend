package com.example.wantudy.study.domain;

import com.example.wantudy.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue
    @Column(name="categoryId")
    private Long categoryId;

    @Column(name="categoryName")
    private String categoryName;

    @OneToMany(mappedBy = "category")
//    @JsonBackReference
    @Builder.Default
    private List<StudyCategory> studies = new ArrayList<>();

//    public static Category createCategory(String categoryName){
//        return Category.builder().categoryName(categoryName).build();
//    }
//
//    public void putStudy(StudyCategory study){
//        this.studies.add(study);
//    }
}
