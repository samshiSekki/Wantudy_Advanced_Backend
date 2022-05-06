package com.example.wantudy.study.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "studyFile")
@Setter
@Builder
public class StudyFile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="studyFileId")
    private Long studyFileId;

    @Column(name="fileName")
    private String fileName;

    @Column(name="filePath")
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

    //Study 정보 저장
    public void setStudy(Study study){
        this.study = study;
    }
}
