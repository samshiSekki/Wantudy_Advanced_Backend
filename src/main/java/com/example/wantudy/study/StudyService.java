package com.example.wantudy.study;

import com.example.wantudy.study.domain.*;
import com.example.wantudy.study.dto.StudyDetailResponseDto;
import com.example.wantudy.study.repository.*;
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

    public Study findByStudyId(long studyId) {
        Optional<Study> study = studyRepository.findById(studyId);
        return study.orElse(null);
    }

    public StudyDetailResponseDto getOneStudy(Study study) {
        StudyDetailResponseDto studyDetailResponseDto = StudyDetailResponseDto.from(study);

        //카테고리, 필수정보, 희망시간 리스트 매칭
        studyDetailResponseDto.setCategories(this.getCategory(study));
        studyDetailResponseDto.setDesiredTime(this.getDesiredTime(study));
        studyDetailResponseDto.setRequiredInfo(this.getRequiredInfo(study));

        return studyDetailResponseDto;
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
}
