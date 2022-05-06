package com.example.wantudy.study.domain;

import com.example.wantudy.users.User;
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
    private List<StudyCategory> studies = new ArrayList<>();

}
