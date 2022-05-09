package com.example.wantudy.study.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name="study_category",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"study_id","category_id"}
                )
        }
)
public class StudyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

//    //==연관관계 메서드==//
//
//    public void addStudyAndCategory(Study study, Category category){
//        this.study =study;
//        this.category = category;
//        study.getCategories().add(this);
//        category.getStudies().add(this);
//    }
}
