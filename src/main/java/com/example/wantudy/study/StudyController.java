package com.example.wantudy.study;

import com.example.wantudy.study.domain.StudyStatus;
import com.example.wantudy.study.dto.*;
import com.example.wantudy.study.repository.StudySpec;
import com.example.wantudy.study.service.AwsS3Service;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final AwsS3Service s3Service;

    @ApiOperation("스터디 전체 조회")
    @GetMapping("")
    public EntityResponseDto getAllStudy(@PageableDefault(size=5, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable){

        Specification<Study> spec = (root, query, criteriaBuilder) -> null;

        StudyStatus status = StudyStatus.RECRUIT;
        spec = spec.and(StudySpec.equalStatus(status));

//        Page<StudyAllResponseDto> responseData = studyService.getAllStudy(pageable);

        //모집중인 스터디만 뜨기
        Page<StudyAllResponseDto> responseData = studyService.getStudySearch(spec, pageable);
        if(!CollectionUtils.isEmpty(responseData.getContent()))
            return new EntityResponseDto(200, "스터디 조회 성공(모집중인 스터디만 조회)", responseData);

        return new EntityResponseDto(404, "페이지가 없습니다.", null);
    }

    @ApiOperation("스터디 조건 조회")
    @GetMapping("/filter")
    public EntityResponseDto search(@RequestParam(required = false) String studyName, @RequestParam(required = false) String location,
                                            @RequestParam(required = false, name = "status") StudyStatus status,
                                          @PageableDefault(size=5, sort="createAt", direction = DESC) Pageable pageable){
        Specification<Study> spec = (root, query, criteriaBuilder) -> null;

        if(studyName != null){
            spec = spec.and(StudySpec.likeStudyName(studyName));
        }
        if(location != null){
            spec = spec.and(StudySpec.likeLocation(location));
        }
        if(status != null){
            spec = spec.and(StudySpec.equalStatus(status));
        }

        Page<StudyAllResponseDto> responseData = studyService.getStudySearch(spec, pageable);

        if(!CollectionUtils.isEmpty(responseData.getContent()))
            return new EntityResponseDto(200, "스터디 조건 조회 성공", responseData);

        return new EntityResponseDto(404, "페이지가 없습니다.", null);
    }

    @ApiOperation("스터디 상세 조회")
    @GetMapping("/{studyId}")
    public EntityResponseDto getOneStudy(@ApiParam(value="스터디 ID", required = true) @PathVariable("studyId") long studyId) {

        Study study = studyService.findByStudyId(studyId);
        StudyDetailResponseDto studyDetailResponseDto = studyService.getOneStudy(study);

        return new EntityResponseDto(200, "스터디 상세 페이지 조회", studyDetailResponseDto);
    }

    @ApiOperation("스터디 개설")
    @PostMapping(consumes = {"multipart/form-data"})
    public EntityResponseDto createStudy(@ModelAttribute StudyCreateDto studyCreateDto) throws Exception{

        Study study = new Study(studyCreateDto.getStudyName(), studyCreateDto.getDescription(), studyCreateDto.getLevel(),
                studyCreateDto.getFormat(), studyCreateDto.getLocation(), studyCreateDto.getPeriod(), studyCreateDto.getPeopleNum(),
                studyCreateDto.getDeadline()); // DTO에서 리스트 제외한 필드 가져와서 스터디 객체 만듦

        if(!CollectionUtils.isEmpty(studyCreateDto.getMultipartFile())) {
            //파일이 존재한다면 파일 수 만큼 for문 돌리면서 StudyFile 객체들의 리스트 생성해줌
            for (int i = 0; i < studyCreateDto.getMultipartFile().size(); i++) {

                StudyFileUploadDto studyFileUploadDto = s3Service.upload(studyCreateDto.getMultipartFile().get(i));
                String fileName = studyCreateDto.getMultipartFile().get(i).getOriginalFilename();

                List<String> studyFilePath = List.of(studyFileUploadDto.getFilepath());
                List<String> s3FileName = List.of(studyFileUploadDto.getS3FileName());
                List<String> studyFileName = List.of(fileName);

                studyService.saveStudy(study);
                studyService.saveStudyFiles(studyFilePath, studyFileName, s3FileName, study);
            }
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

        StudyDetailResponseDto studyDetailResponseDto = studyService.getOneStudy(study);

        return new EntityResponseDto(201, "스터디 등록", studyDetailResponseDto);
    }


    @ApiOperation("스터디 수정")
    @PatchMapping("/{studyId}")
    public EntityResponseDto updateStudy(@PathVariable("studyId") long studyId, @ModelAttribute StudyCreateDto studyCreateDto) throws IOException {

        studyService.updateStudy(studyId, studyCreateDto);

        List<String> categories = new ArrayList<>();
        List<String> requiredInfoList = new ArrayList<>();
        List<String> desiredTimeList = new ArrayList<>();

        for (int i = 0; i < studyCreateDto.getCategories().size(); i++) {
            String category = studyCreateDto.getCategories().get(i);
            categories.add(category);
        }

        for (int i = 0; i < studyCreateDto.getRequiredInfo().size(); i++) {
            String requiredInfo = studyCreateDto.getRequiredInfo().get(i);
            requiredInfoList.add(requiredInfo);
        }

        for (int i = 0; i < studyCreateDto.getDesiredTime().size(); i++) {
            String desiredTime = studyCreateDto.getDesiredTime().get(i);
            desiredTimeList.add(desiredTime);
        }

        //버킷에서 파일 삭제
        s3Service.deleteStudyAndFile(studyId);

        //DB에서 파일과 연관관계 리스트 삭제
        studyService.deleteStudyFileForUpdate(studyId);
        studyService.deleteListForUpdate(studyId);

        Study study = studyService.findByStudyId(studyId);

        studyService.saveCategory(categories, study);
        studyService.saveRequiredInfo(requiredInfoList,study);
        studyService.saveDesiredTime(desiredTimeList,study);

        //파일 수 만큼 for문 돌리면서 StudyFile 객체들의 리스트 생성해줌
        for (int i = 0; i < studyCreateDto.getMultipartFile().size(); i++) {

            StudyFileUploadDto studyFileUploadDto = s3Service.upload(studyCreateDto.getMultipartFile().get(i));
            String fileName = studyCreateDto.getMultipartFile().get(i).getOriginalFilename();

            List<String> studyFilePath = List.of(studyFileUploadDto.getFilepath());
            List<String> s3FileName = List.of(studyFileUploadDto.getS3FileName());
            List<String> studyFileName = List.of(fileName);

            studyService.updateStudyFiles(studyFilePath, studyFileName, s3FileName, studyId);
        }

        StudyDetailResponseDto studyDetailResponseDto = studyService.getOneStudy(study);

        return new EntityResponseDto(200, "스터디 수정", studyDetailResponseDto);
    }



    @ApiOperation("스터디 삭제")
    @DeleteMapping("/{studyId}")
    public EntityResponseDto.messageResponse deleteStudy(@PathVariable("studyId") long studyId) {

        s3Service.deleteStudyAndFile(studyId);
        studyService.deleteStudy(studyId);

        return new EntityResponseDto.messageResponse(204, "스터디 삭제 완료");
    }

    @ApiOperation("스터디 파일 다운로드")
    @GetMapping("/file/{studyFileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("studyFileId") long studyFileId) throws IOException {

        byte[] bytes = s3Service.getObject(studyFileId).getBytes();
        String fileName = s3Service.getObject(studyFileId).getFileName();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation("스터디 파일 삭제")
    @DeleteMapping("/file/{studyFileId}")
    public EntityResponseDto.messageResponse deleteFile(@PathVariable("studyFileId") long studyFileId) {
        s3Service.deleteOnlyFile(studyFileId);
        studyService.deleteStudyFile(studyFileId);
        return new EntityResponseDto.messageResponse(204, "파일 삭제 완료");
    }
}
