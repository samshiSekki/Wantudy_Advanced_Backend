package com.example.wantudy.study.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyRequiredInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyRequiredInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name="studyId")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name="requiredInfoId")
    private RequiredInfo requiredInfo;

}
