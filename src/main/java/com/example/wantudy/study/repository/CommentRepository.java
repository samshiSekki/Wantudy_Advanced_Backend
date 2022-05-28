package com.example.wantudy.study.repository;

import com.example.wantudy.study.Study;
import com.example.wantudy.study.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
    List<Comment> findByStudy(Study study);
}
