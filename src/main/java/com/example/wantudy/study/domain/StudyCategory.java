package com.example.wantudy.study.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonManagedReference
    @JoinColumn(name="studyId")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonManagedReference
    @JoinColumn(name="categoryId")
    private Category category;

    //==생성 메서드==//
    public static StudyCategory createStudyCategory(Category category){
        StudyCategory studyCategory = new StudyCategory();
        studyCategory.setCategory(category);

        return studyCategory;
    }
}
