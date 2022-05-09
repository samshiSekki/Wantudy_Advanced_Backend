package com.example.wantudy.study.domain;

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
    public StudyFile(Long studyFileId, String fileName, String filePath) {
        this.studyFileId = studyFileId;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    // study 정보 저장
    public void setStudy(Study study){
        this.study = study;
    }
}
