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
public class DesiredTime {

    @Id
    @GeneratedValue
    @Column(name="desiredTimeId")
    private Long desiredTimeId;

    @Column
    private String desiredTime;

    @OneToMany(mappedBy = "desiredTime")
    private List<StudyDesiredTime> studies = new ArrayList<>();

}
