package com.example.wantudy.study;

import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.dto.StudyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/study")
@Transactional
public class StudyController {

    private final StudyService studyService;


    @Autowired
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/test")
    public String test(){
        return "so sad......";
    }

}
