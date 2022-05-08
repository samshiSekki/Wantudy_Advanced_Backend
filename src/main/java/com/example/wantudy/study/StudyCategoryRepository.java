package com.example.wantudy.study;

import com.example.wantudy.study.domain.Category;
import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.domain.StudyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyCategoryRepository extends JpaRepository<StudyCategory, Long> {
}
