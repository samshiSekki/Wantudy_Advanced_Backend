package com.example.wantudy.study.repository;

import com.example.wantudy.study.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long>, JpaSpecificationExecutor<Study> {
    Study findByStudyName(String studyName);
    Page<Study> findAll(Specification<Study> spec, Pageable pageable);
//    Page<Study> findByStudyNameContaining(String studyName, Pageable pageable);
//    Page<Study> findByCategoriesContaining(List<String> categories, Pageable pageable);
}
