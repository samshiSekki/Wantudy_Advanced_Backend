package com.example.wantudy.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@AllArgsConstructor
public class EntityResponseDto {

    private int status;
    private String message;
    private Object data;


    @Getter
    @AllArgsConstructor
    public static class messageResponse{
        private int status;
        private String message;
    }

}
