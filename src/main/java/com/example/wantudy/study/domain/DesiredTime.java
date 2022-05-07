package com.example.wantudy.study.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
//    @JsonBackReference
    @Builder.Default
    private List<StudyDesiredTime> studies = new ArrayList<>();

}
