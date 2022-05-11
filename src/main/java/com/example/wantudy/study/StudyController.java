package com.example.wantudy.study;

import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.dto.EntityResponseDto;
import com.example.wantudy.study.dto.StudyDetailResponseDto;
import com.example.wantudy.study.dto.StudyCreateDto;
import com.example.wantudy.study.service.AwsS3Service;
import lombok.RequiredArgsConstructor;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final AwsS3Service s3Service;

    @GetMapping("/{studyId}")
    public ResponseEntity<EntityResponseDto> getOneStudy(@PathVariable("studyId") long studyId) {

        Study study = studyService.findByStudyId(studyId);
        StudyDetailResponseDto studyDetailResponseDto = studyService.getOneStudy(study);

        return new ResponseEntity<>(new EntityResponseDto(200, "스터디 상세 페이지 조회", studyDetailResponseDto), HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<EntityResponseDto> createStudy(
            @RequestPart(value="studyCreateDto") StudyCreateDto studyCreateDto,
            @RequestPart(value="file",required = false) List<MultipartFile> multipartFile) throws Exception {

        Study study = new Study(studyCreateDto.getStudyName(), studyCreateDto.getDescription(), studyCreateDto.getLevel(),
                studyCreateDto.getFormat(),studyCreateDto.getLocation(), studyCreateDto.getPeriod(), studyCreateDto.getPeopleNum(),
                studyCreateDto.getDeadline()); // DTO에서 리스트 제외한 필드 가져와서 스터디 객체 만듦

        //파일 수 만큼 for문 돌리면서 StudyFile 객체들의 리스트 생성해줌
        for(int i = 0; i < studyCreateDto.getStudyFileNames().size(); i++){
            String filePath = s3Service.upload(multipartFile.get(i));
            String fileName = studyCreateDto.getStudyFileNames().get(i);
            List<String> studyFilePath = List.of(filePath);
            List<String> studyFileName = List.of(fileName);
            studyService.saveStudy(study);
            studyService.saveStudyFiles(studyFilePath, studyFileName, study);
        }

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
        return new ResponseEntity<>(new EntityResponseDto(201,"스터디 등록",study), HttpStatus.CREATED);
    }

}
