package com.example.wantudy.study.domain;

import com.example.wantudy.application.domain.Application;
import com.example.wantudy.application.domain.ApplicationInterests;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="category_id")
    private Long categoryId;

    @Column(name="category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<StudyCategory> studies = new ArrayList<>();

    // category : applicationCategory = 1:N
    @OneToMany(mappedBy = "category", targetEntity = ApplicationInterests.class)
    @JsonManagedReference
    private List<ApplicationInterests> applicationInterests = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @Builder
    public Category(String categoryName){
        this.categoryName = categoryName;
    }

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
