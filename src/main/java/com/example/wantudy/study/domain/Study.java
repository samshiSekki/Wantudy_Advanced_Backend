package com.example.wantudy.study.domain;

import com.example.wantudy.study.dto.StudyCreateDto;
import com.example.wantudy.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "study")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Study {

    @Id
    @Column(name = "study_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;

    //연관관계 주인
    //이 값을 변경하면 user_id foreign key값이 변경됨
    //study입장에서 User랑 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //매핑 키 설정
    private User user;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    @Column(name = "categories")
//    @JsonBackReference
    @Builder.Default
    private List<StudyCategory> categories = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    @Column(name = "desiredTime")
//    @JsonBackReference
    @Builder.Default
    private List<StudyDesiredTime> desiredTime = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    @Column(name = "requiredInfo")
//    @JsonBackReference
    @Builder.Default
    private List<StudyRequiredInfo> requiredInfo = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "studyFile")
    @Builder.Default
    private List<StudyFile> studyFiles = new ArrayList<>();

    @Column(name = "fixedStudySchedule")
    private String fixedStudySchedule;

    @Column(name = "studyName", length = 50)
    private String studyName;

    @Column(name = "description")
    private String description;

    @Column(name = "level")
    private String level;

    @Column(name = "format")
    private String format;

    @Column(name = "location")
    private String location;

    @Column(name = "peopleNum")
    private Number peopleNum;

    @Column(name = "currentNum")
    private Number currentNum;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "period")
    private String period;

    @Enumerated(EnumType.STRING)
    private StudyStatus studyStatus;

    @Column(name = "likeNum")
    private Number likeNum;

    @Column(name = "createAt")
    private LocalDateTime createAt;


//    @PrePersist
//    public void createdAt() {
//        this.createAt = LocalDateTime.now();
//    }


//    //==연관관계 메서드==//
//    public void addStudyCategory(StudyCategory studyCategory){
//        categories.add(studyCategory);
//        studyCategory.setStudy(this);
//    }
//
//    //==생성 메서드==//
//    public static Study createStudy(StudyCreateDto studyCreateDto, StudyCategory... categories){
//        Study study = new Study(studyCreateDto);
//        for(StudyCategory studyCategory : categories){
//            study.addStudyCategory(studyCategory);
//        }
//        study.setStudyStatus(StudyStatus.RECRUIT);
//        study.setCreateAt(LocalDateTime.now());
//        return study;
//    }
}
