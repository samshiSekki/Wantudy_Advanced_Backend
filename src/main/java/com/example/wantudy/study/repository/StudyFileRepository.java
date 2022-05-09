package com.example.wantudy.study.repository;

import com.example.wantudy.study.domain.StudyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyFileRepository extends JpaRepository<StudyFile, Long> {
}
