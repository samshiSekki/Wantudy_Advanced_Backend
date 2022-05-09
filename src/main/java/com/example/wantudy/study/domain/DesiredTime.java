package com.example.wantudy.study.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesiredTime {

    @Id
    @GeneratedValue
    @Column(name="desired_time_id")
    private Long desiredTimeId;

    @Column(name="desired_time")
    private String desiredTime;

    @OneToMany(mappedBy = "desiredTime")
    private List<StudyDesiredTime> studies = new ArrayList<>();

    @Builder
    public DesiredTime(String desiredTime){
        this.desiredTime = desiredTime;
    }

}
