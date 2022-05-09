package com.example.wantudy.study.repository;

import com.example.wantudy.study.domain.DesiredTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesiredTimeRepository extends JpaRepository<DesiredTime, Long> {
    DesiredTime findByDesiredTime(String DesiredTime);
}
