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
public class RequiredInfo {

    @Id
    @GeneratedValue
    @Column
    private Long requiredInfo;

    @Column
    private String requiredInfoName;

    @OneToMany(mappedBy = "requiredInfo")
//    @JsonBackReference
    @Builder.Default
    private List<StudyRequiredInfo> studies = new ArrayList<>();
}
