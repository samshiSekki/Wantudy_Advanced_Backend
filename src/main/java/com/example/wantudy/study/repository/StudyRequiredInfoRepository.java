package com.example.wantudy.study.repository;

import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.domain.StudyRequiredInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRequiredInfoRepository extends JpaRepository<StudyRequiredInfo, Long> {
    List<StudyRequiredInfo> findByStudy(Study study);
}
