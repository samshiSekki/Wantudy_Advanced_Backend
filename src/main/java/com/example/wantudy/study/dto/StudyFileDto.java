package com.example.wantudy.study.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class StudyFileDto {

    private Long studyFileId;
    private String fileName;
    private String filePath;

    @Getter
    @AllArgsConstructor
    public static class downloadFileResponse{
        private byte[] bytes;
        private String fileName;
    }

}
