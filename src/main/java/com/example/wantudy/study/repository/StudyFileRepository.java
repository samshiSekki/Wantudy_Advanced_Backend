package com.example.wantudy.study.repository;

import com.example.wantudy.study.Study;
import com.example.wantudy.study.domain.StudyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyFileRepository extends JpaRepository<StudyFile, Long> {
    List<StudyFile> findByStudy(Study study);
}
