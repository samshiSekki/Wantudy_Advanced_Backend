package com.example.wantudy.study;

import com.example.wantudy.study.domain.*;
import com.example.wantudy.study.dto.StudyCreateDto;
import com.example.wantudy.study.dto.StudyDetailResponseDto;
import com.example.wantudy.study.dto.StudyFileDto;
import com.example.wantudy.study.repository.*;
import com.example.wantudy.study.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor //final에 있는 애로 생성자 만들어줌 (lombok의 기능)
public class StudyService {

    private final StudyRepository studyRepository;
    private final CategoryRepository categoryRepository;
    private final StudyCategoryRepository studyCategoryRepository;

    private final RequiredInfoRepository requiredInfoRepository;
    private final StudyRequiredInfoRepository studyRequiredInfoRepository;

    private final DesiredTimeRepository desiredTimeRepository;
    private final StudyDesiredTimeRepository studyDesiredTimeRepository;

    private final StudyFileRepository studyFileRepository;

    private final AwsS3Service s3Service;

    public Study findByStudyId(long studyId) {
        Optional<Study> study = studyRepository.findById(studyId);
        return study.orElse(null);
    }

    public StudyDetailResponseDto getOneStudy(Study study) {
        StudyDetailResponseDto studyDetailResponseDto = StudyDetailResponseDto.from(study);

        //카테고리, 필수정보, 희망시간, 파일 리스트 매칭
        studyDetailResponseDto.setCategories(this.getCategory(study));
        studyDetailResponseDto.setDesiredTime(this.getDesiredTime(study));
        studyDetailResponseDto.setRequiredInfo(this.getRequiredInfo(study));
        studyDetailResponseDto.setStudyFiles(this.getStudyFiles(study));

        return studyDetailResponseDto;
    }

    public List<StudyFileDto> getStudyFiles(Study fileStudy){
        Optional<Study> study = studyRepository.findById(fileStudy.getStudyId());

        List<StudyFileDto> files = new ArrayList<>();
        List<StudyFile> studyFiles = studyFileRepository.findByStudy(study.get());

        for(int i=0;i<studyFiles.size(); i++) {
            StudyFileDto fileDto = new StudyFileDto();
            fileDto.setStudyFileId(studyFiles.get(i).getStudyFileId());
            fileDto.setFileName(studyFiles.get(i).getFileName());
            fileDto.setFilePath(studyFiles.get(i).getFilePath());
            files.add(fileDto);
        }
        return files;
    }

    public List<String> getCategory(Study categoryStudy){
        Optional<Study> study = studyRepository.findById(categoryStudy.getStudyId());
        List<String> categories = new ArrayList<>();
        List<StudyCategory> studyCategories = studyCategoryRepository.findByStudy(study.get());
        for(int i=0;i<studyCategories .size();i++)
            categories.add(studyCategories.get(i).getCategory().getCategoryName());
        return categories;
    }

    public List<String> getRequiredInfo(Study requiredInfoStudy){
        Optional<Study> study = studyRepository.findById(requiredInfoStudy.getStudyId());

        List<String> requiredInfoList = new ArrayList<>();
        List<StudyRequiredInfo> studyRequiredInfoList = studyRequiredInfoRepository.findByStudy(study.get());
        for(int i=0;i<studyRequiredInfoList .size();i++)
            requiredInfoList.add(studyRequiredInfoList.get(i).getRequiredInfo().getRequiredInfoName());
        return requiredInfoList;
    }

    public List<String> getDesiredTime(Study desiredTimeStudy){
        Optional<Study> study = studyRepository.findById(desiredTimeStudy.getStudyId());

        List<String> desiredTimeList = new ArrayList<>();
        List<StudyDesiredTime> studyDesiredTimeList  = studyDesiredTimeRepository.findByStudy(study.get());
        for(int i=0;i<studyDesiredTimeList.size();i++)
            desiredTimeList.add(studyDesiredTimeList.get(i).getDesiredTime().getDesiredTime());
        return desiredTimeList;
    }

    public long saveStudy(Study study){
        Study createStudy = studyRepository.save(study);
        return createStudy.getStudyId();
    }

//    public Study saveStudyDtoToEntity(StudyCreateDto studyCreateDto){
//        Study saveStudy = studyCreateDto.toEntity();
//        return studyRepository.save(saveStudy);
//    }

    public void saveCategory(List<String> categories, Study study){
        for (int i = 0; i < categories.size(); i++){
            Optional<Category> existedCategory = Optional.ofNullable(categoryRepository.findByCategoryName(categories.get(i)));
            StudyCategory studyCategory = new StudyCategory();

            if(existedCategory.isPresent()) {
                studyCategory.setCategory(existedCategory.get());
            }
            else {
                Category category= new Category(categories.get(i));
                categoryRepository.save(category);
                studyCategory.setCategory(category);
            }
            studyCategory.setStudy(study);
            studyCategoryRepository.save(studyCategory);
        }
    }

    public void saveRequiredInfo(List<String> requiredInfoList, Study study) {
        for (int i = 0; i < requiredInfoList.size(); i++){
            Optional<RequiredInfo> existedRequiredInfo= Optional.ofNullable(requiredInfoRepository.findByRequiredInfoName(requiredInfoList.get(i)));
            StudyRequiredInfo studyRequiredInfo = new StudyRequiredInfo();

            if(existedRequiredInfo.isPresent()) {
                studyRequiredInfo.setRequiredInfo(existedRequiredInfo.get());
            }
            else {
                RequiredInfo requiredInfo = new RequiredInfo(requiredInfoList.get(i));
                requiredInfoRepository.save(requiredInfo);
                studyRequiredInfo.setRequiredInfo(requiredInfo);
            }
            studyRequiredInfo.setStudy(study);
            studyRequiredInfoRepository.save(studyRequiredInfo);
        }
    }

    public void saveDesiredTime(List<String> desiredTimeList, Study study) {
        for (int i = 0; i < desiredTimeList.size(); i++){
            Optional<DesiredTime> existedDesiredTime= Optional.ofNullable(desiredTimeRepository.findByDesiredTime(desiredTimeList.get(i)));
            StudyDesiredTime studyDesiredTime= new StudyDesiredTime();

            if(existedDesiredTime.isPresent()) {
                studyDesiredTime.setDesiredTime(existedDesiredTime.get());
            }
            else {
                DesiredTime desiredTime = new DesiredTime(desiredTimeList.get(i));
                desiredTimeRepository.save(desiredTime);
                studyDesiredTime.setDesiredTime(desiredTime);
            }
            studyDesiredTime.setStudy(study);
            studyDesiredTimeRepository.save(studyDesiredTime);
        }
    }

    // 파일 객체 DB에 저장
    public void saveStudyFiles(List<String> studyFilePath, List<String> studyFileName,  List<String> s3FileName, Study study){

        for (int i = 0; i < studyFilePath.size(); i++){
            StudyFile studyFile = new StudyFile();

            studyFile.setStudy(study);
            studyFile.setFilePath(studyFilePath.get(i));
            studyFile.setFileName(studyFileName.get(i));
            studyFile.setS3FileName(s3FileName.get(i));

            study.addStudyFiles(studyFile);
            studyFileRepository.save(studyFile);
        }
    }

    public String downloadFile(long studyFileId) {
        Optional<StudyFile> studyFile = studyFileRepository.findById(studyFileId);
        return studyFile.get().getFilePath();
    }

    public void deleteStudy(long studyId) {
        Optional<Study> study= studyRepository.findById(studyId);
        studyRepository.delete(study.get());
    }

    public void deleteStudyFile(long studyFileId) {
        Optional<StudyFile> studyFile = studyFileRepository.findById(studyFileId);
        studyFileRepository.delete(studyFile.get());
    }

//    public Study updateStudy(StudyCreateDto studyCreateDto, Long studyId) {
////      Optional<Study> study= studyRepository.findById(studyId);
//        study.get()set
//
//    }
}
