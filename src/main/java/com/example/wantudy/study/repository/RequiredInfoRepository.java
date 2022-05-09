package com.example.wantudy.study.repository;

import com.example.wantudy.study.domain.RequiredInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequiredInfoRepository extends JpaRepository<RequiredInfo, Long> {
    RequiredInfo findByRequiredInfoName(String requiredInfoName);

}
