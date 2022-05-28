package com.example.wantudy.study.repository;

import com.example.wantudy.study.dto.CommentResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositoryCustom {
    List<CommentResponseDto> findCommentByStudyId(long studyId);
}
