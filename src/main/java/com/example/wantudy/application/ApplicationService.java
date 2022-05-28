package com.example.wantudy.application;

import com.example.wantudy.application.domain.*;
import com.example.wantudy.application.dto.ApplicationCreateDto;
import com.example.wantudy.application.repository.ApplicationInterestsRepository;
import com.example.wantudy.application.repository.ApplicationKeywordRepository;
import com.example.wantudy.application.repository.ApplicationRepository;
import com.example.wantudy.application.repository.KeywordRepository;
import com.example.wantudy.oauth.User;
import com.example.wantudy.study.domain.Category;
import com.example.wantudy.study.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;
    private final ApplicationInterestsRepository applicationInterestsRepository;
    private final ApplicationKeywordRepository applicationKeywordsRepository;

    // 특정 지원서 받아오기
    public Application findByApplicationId(Long applicationId){
        Optional<Application> application = applicationRepository.findById(applicationId);
        System.out.println("application.get().getApplicationName() = " + application.get().getApplicationName());
        return applicationRepository.findById(applicationId).orElse(null);
    }

    // 지원서 작성
    public Application saveApplication(User user, ApplicationCreateDto applicationCreateDto){
        /**
         * String으로 받아온 값 Enum 으로 변경 후 entity 생성
         */
        String genderStr = applicationCreateDto.getGender(); // 여자라고 들어오면 value 값을 갖고 code 찾기
        Gender gender = Arrays.stream(Gender.values()) // Gender Enum MALE, FEMALE 에서 title 값이 들어온 string 과 동일한 애 찾기
                .filter(o1 -> o1.getTitle().equals(genderStr))
                .findFirst()
                .get();

        String attendanceStr = applicationCreateDto.getAttendance();
        Attendance attendance = Arrays.stream(Attendance.values())
                .filter(o1 -> o1.getTitle().equals(attendanceStr))
                .findFirst()
                .get();

        Application application = applicationCreateDto.toEntity(user, gender, attendance);

        List<String> interests = applicationCreateDto.getInterests();
        List<String> keywords = applicationCreateDto.getKeywords();
        applicationRepository.save(application);
        saveInterests(interests, application);
        saveKeywords(keywords, application);
        return application;
    }

    // 지원서 작성 시 관심분야 (카테고리) 저장
    public void saveInterests(List<String> interests, Application application){
        for(int i=0;i<interests.size();i++){
            Category category = categoryRepository.findByCategoryName(interests.get(i));
            ApplicationInterests applicationInterests;
            if(category == null){ // 키워드가 없는 경우 생성
                Category newCategory = new Category(interests.get(i));
                categoryRepository.save(newCategory);
                applicationInterests = ApplicationInterests.toEntity(application, newCategory);
            }
            else
                applicationInterests = ApplicationInterests.toEntity(application, category);
            applicationInterestsRepository.save(applicationInterests);
        }
    }

    // 지원서 작성 시 키워드 저장
    public void saveKeywords(List<String> keywords, Application application){
        for(int i=0;i<keywords.size();i++){
            Keyword keyword = keywordRepository.findByKeyword(keywords.get(i)).orElse(null);
            ApplicationKeyword applicationKeyword;
            if(keyword == null){ // 키워드가 없는 경우 생성
                Keyword newKeyword = new Keyword(keywords.get(i));
                keywordRepository.save(newKeyword);
                applicationKeyword = ApplicationKeyword.toEntity(application, newKeyword);
            }
            else
                applicationKeyword = ApplicationKeyword.toEntity(application, keyword);
            applicationKeywordsRepository.save(applicationKeyword);
        }
    }

    // 지원서 작성
    public void deleteApplication(Application application){
        applicationRepository.delete(application);
    }

//    //  저장
//    public void manageHashtag(List<String> hashtags, Lecture lecture){
//        for (int i = 0; i < hashtags.size(); i++) {
//            Optional<Hashtag> existedHashtag = hashtagRepository.findByHashtagName(hashtags.get(i));
//            LectureHashtag lectureHashtag = new LectureHashtag();
//            if(existedHashtag.isPresent()) { // 이미 들어간 해시태그라면 id 받아오기
//                lectureHashtag.setHashtag(existedHashtag.get());
//            }
//            else { // 없는 해시태그라면 해시태그를 생성하고 나서 lectureHashtag에 넣기
//                Hashtag hashtag = new Hashtag(hashtags.get(i));
//                hashtagRepository.save(hashtag);
//                lectureHashtag.setHashtag(hashtag);
//            }
//            lectureHashtag.setLecture(lecture);
//            lectureHashtagRepository.save(lectureHashtag);
//        }
//    }

    // 지원서 수정
    /*public void updateApplication(LectureDto lectureDto, Long lectureId){
        lectureRepository.updateLecture(lectureDto, lectureId);
    }*/
}
