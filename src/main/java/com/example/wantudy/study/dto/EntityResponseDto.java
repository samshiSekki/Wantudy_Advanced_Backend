package com.example.wantudy.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EntityResponseDto {

    private int status;
    private String message;
    private Object data;

    public static EntityResponseDto withData(final int status, final String msg, final Object data) {
        return EntityResponseDto.builder()
                .data(data)
                .message(msg)
                .status(status).build();
    }
}
