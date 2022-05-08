package com.example.wantudy.study.domain;

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
    @Column(name="category_id")
    private Long categoryId;

    @Column(name="category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    @Builder.Default
    private List<StudyCategory> studies = new ArrayList<>();

}
