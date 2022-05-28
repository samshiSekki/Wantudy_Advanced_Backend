package com.example.wantudy.application;

import com.example.wantudy.application.domain.Application;
import com.example.wantudy.application.domain.ApplicationInterests;
import com.example.wantudy.application.domain.ApplicationKeyword;
import com.example.wantudy.application.domain.Keyword;
import com.example.wantudy.application.dto.ApplicationCreateDto;
import com.example.wantudy.application.repository.ApplicationInterestsRepository;
import com.example.wantudy.application.repository.ApplicationKeywordRepository;
import com.example.wantudy.application.repository.ApplicationRepository;
import com.example.wantudy.application.repository.KeywordRepository;
import com.example.wantudy.oauth.User;
import com.example.wantudy.study.domain.Category;
import com.example.wantudy.study.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;
    private final ApplicationInterestsRepository applicationInterestsRepository;
    private final ApplicationKeywordRepository applicationKeywordsRepository;

    // 지원서 작성
    public Application saveApplication(User user, ApplicationCreateDto applicationCreateDto){
        Application application = applicationCreateDto.toEntity(user);
        List<String> interests = applicationCreateDto.getInterests();
        List<String> keywords = applicationCreateDto.getKeywords();
        saveInterests(interests, application);
        saveKeywords(keywords, application);
        return applicationRepository.save(application);
    }

    // 지원서 작성 시 관심분야 (카테고리) 저장
    public void saveInterests(List<String> interests, Application application){
        for(int i=0;i<interests.size();i++){
            Category category = categoryRepository.findByCategoryName(interests.get(i));
            ApplicationInterests applicationInterests = ApplicationInterests.toEntity(application, category);
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
