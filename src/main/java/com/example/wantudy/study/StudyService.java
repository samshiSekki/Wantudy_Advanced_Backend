package com.example.wantudy.study;

import com.example.wantudy.study.domain.Study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StudyService {

    private StudyRepository studyRepository;

    @Autowired
    public StudyService(StudyRepository studyRepository){
        this.studyRepository = studyRepository;
    }

}
