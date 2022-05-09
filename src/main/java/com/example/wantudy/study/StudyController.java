package com.example.wantudy.study;

import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.dto.EntityResponseDto;
import com.example.wantudy.study.dto.StudyDetailResponseDto;
import com.example.wantudy.study.dto.StudyCreateDto;
import lombok.RequiredArgsConstructor;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping("/{studyId}")
    public ResponseEntity<EntityResponseDto> getOneStudy(@PathVariable("studyId") long studyId) {

        Study study = studyService.findByStudyId(studyId);
        StudyDetailResponseDto studyDetailResponseDto = studyService.getOneStudy(study);

        return new ResponseEntity<>(EntityResponseDto.withData(200, "스터디 상세 페이지 조회", studyDetailResponseDto), HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<EntityResponseDto> createStudy(@RequestBody StudyCreateDto studyCreateDto) {

        Study study = new Study(studyCreateDto.getStudyName(), studyCreateDto.getDescription(), studyCreateDto.getLevel(),
                studyCreateDto.getFormat(),studyCreateDto.getLocation(), studyCreateDto.getPeriod(), studyCreateDto.getPeopleNum(),
        studyCreateDto.getDeadline()); // DTO에서 리스트 제외한 필드 가져와서 스터디 객체 만듦

        //DTO에서 리스트 정보 값 가져와서 차례대로 넣어주기
        for (int i = 0; i < studyCreateDto.getCategories().size(); i++) {
            String category = studyCreateDto.getCategories().get(i);
            List<String> categories = List.of(category);
            studyService.saveStudy(study);
            studyService.saveCategory(categories, study);
        }

        for (int i = 0; i < studyCreateDto.getDesiredTime().size(); i++) {
            String desiredTime = studyCreateDto.getDesiredTime().get(i);
            List<String> desiredTimeList = List.of(desiredTime);
            studyService.saveStudy(study);
            studyService.saveDesiredTime(desiredTimeList, study);
        }

        for (int i = 0; i < studyCreateDto.getRequiredInfo().size(); i++) {
            String requiredInfo = studyCreateDto.getRequiredInfo().get(i);
            List<String> requiredInfoList = List.of(requiredInfo);
            studyService.saveStudy(study);
            studyService.saveRequiredInfo(requiredInfoList, study);
        }

        return new ResponseEntity<>(EntityResponseDto.withData(201,"스터디 등록",study), HttpStatus.CREATED);
    }

}
