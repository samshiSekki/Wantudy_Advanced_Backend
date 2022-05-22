package com.example.wantudy.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyCategory is a Querydsl query type for StudyCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyCategory extends EntityPathBase<StudyCategory> {

    private static final long serialVersionUID = 73557351L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyCategory studyCategory = new QStudyCategory("studyCategory");

    public final QCategory category;

    public final com.example.wantudy.study.QStudy study;

    public final NumberPath<Long> studyCategoryId = createNumber("studyCategoryId", Long.class);

    public QStudyCategory(String variable) {
        this(StudyCategory.class, forVariable(variable), INITS);
    }

    public QStudyCategory(Path<? extends StudyCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyCategory(PathMetadata metadata, PathInits inits) {
        this(StudyCategory.class, metadata, inits);
    }

    public QStudyCategory(Class<? extends StudyCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.study = inits.isInitialized("study") ? new com.example.wantudy.study.QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

