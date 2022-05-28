package com.example.wantudy.application.domain;

import com.example.wantudy.study.domain.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@Entity
@Table(name="applicationInterests")
public class ApplicationInterests {
    @Id
    @Column(name = "applicationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationInterestsId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Application.class)
    @JoinColumn(name="applicationId")
    @JsonBackReference
    @NotNull
    private Application application;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name="categoryId")
    @JsonBackReference
    @NotNull
    private Category category;

    public static ApplicationInterests toEntity(Application application, Category category){
        return ApplicationInterests
                .builder()
                .application(application)
                .category(category)
                .build();
    }
}
