package com.example.wantudy.domain.application.repository;

import com.example.wantudy.domain.application.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
