package com.example.wantudy.study;

import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.dto.StudyCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping("/test")
    public String test(){
        return "so sad......";
    }

    @PostMapping("/test")
    public Study save(@RequestBody StudyCreateDto studycreateDto){

        return studyService.createStudy(studycreateDto);
    }
}
