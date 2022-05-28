package com.example.wantudy.application.domain;

import com.example.wantudy.oauth.User;
import com.example.wantudy.study.domain.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "application")
@JsonIgnoreProperties(value = {"interests", "keywords"})
public class Application {
    @Id
    @Column(name = "applicationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name="userId")
    @JsonBackReference // 연관관계의 주인
    @NotNull
    private User user;

    @Column
    private String applicationName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Integer age;

    @Column
    private String organization;

    @Column
    private String major;

    @Enumerated(EnumType.STRING)
    private Attendance attendance;

    @Column
    private String semester;

    @Column
    private String address;

    @OneToMany(
            mappedBy = "application",
            targetEntity = ApplicationInterests.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    @Builder.Default
    private List<ApplicationInterests> interests = new ArrayList<>();// 관심 분야

    @OneToMany(
            mappedBy = "application",
            targetEntity = ApplicationKeyword.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    @Builder.Default
    private List<ApplicationKeyword> keywords = new ArrayList<>(); // 자신을 표현하는 키워드
}
