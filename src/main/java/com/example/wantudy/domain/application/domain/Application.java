package com.example.wantudy.domain.application.domain;

import com.example.wantudy.domain.application.dto.ApplicationCreateDto;
import com.example.wantudy.global.security.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
            orphanRemoval = true
    )
    @JsonManagedReference
    @Builder.Default
    private List<ApplicationInterests> interests = new ArrayList<>();// 관심 분야

    @OneToMany(
            mappedBy = "application",
            targetEntity = ApplicationKeyword.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    @Builder.Default
    private List<ApplicationKeyword> keywords = new ArrayList<>(); // 자신을 표현하는 키워드

    public void updateApplication(ApplicationCreateDto dto){
        this.applicationName = dto.getApplicationName() == null ? this.applicationName : dto.getApplicationName();
        this.organization = dto.getOrganization() == null ? this.organization : dto.getOrganization();
        this.major = dto.getMajor() == null ? this.major : dto.getMajor();
        this.semester = dto.getSemester() == null ? this.semester : dto.getSemester();
        this.address = dto.getAddress() == null ? this.address : dto.getAddress();
    }

    public void toGenderEnum(String genderStr) {
        this.gender = Arrays.stream(Gender.values()) // Gender Enum MALE, FEMALE 에서 title 값이 들어온 string 과 동일한 애 찾기
                .filter(o1 -> o1.getTitle().equals(genderStr))
                .findFirst()
                .get();
    }

    public void toAttendanceEnum(String attendanceStr){
        this.attendance = Arrays.stream(Attendance.values())
                .filter(o1 -> o1.getTitle().equals(attendanceStr))
                .findFirst()
                .get();
    }

}
