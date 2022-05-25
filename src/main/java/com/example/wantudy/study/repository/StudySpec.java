package com.example.wantudy.study.repository;

import com.example.wantudy.study.Study;
import com.example.wantudy.study.domain.StudyStatus;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class StudySpec {

    public static Specification<Study> likeStudyName(String studyName) {
       return new Specification<Study>() {
           @Override
           public Predicate toPredicate(Root<Study> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               return cb.like(root.get("studyName"), "%" + studyName +"%");
           }
       };
    }

    public static Specification<Study> likeLocation(String location) {
        return new Specification<Study>() {
            @Override
            public Predicate toPredicate(Root<Study> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("location"), "%" + location +"%");
            }
        };
    }

    public static Specification<Study> equalStatus(StudyStatus status) {
        return new Specification<Study>() {
            @Override
            public Predicate toPredicate(Root<Study> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("studyStatus"), status);
            }
        };
    }
}
