package com.example.wantudy.study;

import com.example.wantudy.study.domain.Category;
import com.example.wantudy.study.domain.Study;
import com.example.wantudy.study.domain.StudyCategory;
import com.example.wantudy.study.dto.CategoryDto;
import com.example.wantudy.study.dto.StudyCategoryDto;
import com.example.wantudy.study.dto.StudyCreateDto;
import com.example.wantudy.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor //final에 있는 애로 생성자 만들어줌 (lombok의 기능)
public class StudyService {

    private final StudyRepository studyRepository;

    public Study createStudy(StudyCreateDto studyCreateDto) {

        return studyRepository.save(studyCreateDto.toEntity());
    }
}
