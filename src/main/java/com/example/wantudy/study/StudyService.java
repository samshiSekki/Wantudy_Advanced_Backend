package com.example.wantudy.study;

import com.example.wantudy.study.domain.Category;
import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.domain.StudyCategory;
import com.example.wantudy.study.dto.StudyCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor //final에 있는 애로 생성자 만들어줌 (lombok의 기능)
public class StudyService {


    private final StudyRepository studyRepository;
    private final CategoryRepository categoryRepository;

    public Study createStudy(StudyCreateDto studyCreateDto) {
        System.out.println(studyCreateDto.getCategories());

        Study study = new Study();

        for(int i =0; i<studyCreateDto.getCategories().size(); i++){
            Category category = categoryRepository.findByCategoryName(studyCreateDto.getCategories().get(i));
            StudyCategory studyCategory = new StudyCategory();
            studyCategory.setCategory(category);
            studyCategory.setStudy(study);
            study.addCategory(studyCategory);
        }

        study.setStudyName(studyCreateDto.getStudyName());

        return studyRepository.save(study);

    }
}
