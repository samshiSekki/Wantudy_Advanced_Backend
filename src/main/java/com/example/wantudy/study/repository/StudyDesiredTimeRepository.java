package com.example.wantudy.study.repository;

import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.domain.StudyDesiredTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyDesiredTimeRepository extends JpaRepository<StudyDesiredTime, Long> {
    List<StudyDesiredTime> findByStudy(Study study);
}
