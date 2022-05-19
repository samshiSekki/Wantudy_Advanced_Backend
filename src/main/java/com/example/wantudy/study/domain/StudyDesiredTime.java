package com.example.wantudy.study.domain;

import com.example.wantudy.study.Study;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyDesiredTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyDesiredTimeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="desired_time_id")
    private DesiredTime desiredTime;

}
