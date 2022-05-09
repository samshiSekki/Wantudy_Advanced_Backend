package com.example.wantudy.study.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class StudyFile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="study_file_id")
    private Long studyFileId;

    @Column(name="file_name")
    private String fileName;

    @Column(name="file_path")
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_Id")
    private Study study;

    @Builder
    public StudyFile(Long studyFileId, String fileName, String filePath, Study study) {
        this.studyFileId = studyFileId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.study = study;
    }
}
