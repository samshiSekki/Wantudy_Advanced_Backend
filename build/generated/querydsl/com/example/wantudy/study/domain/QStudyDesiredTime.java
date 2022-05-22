package com.example.wantudy.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyDesiredTime is a Querydsl query type for StudyDesiredTime
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyDesiredTime extends EntityPathBase<StudyDesiredTime> {

    private static final long serialVersionUID = -608134274L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyDesiredTime studyDesiredTime = new QStudyDesiredTime("studyDesiredTime");

    public final QDesiredTime desiredTime;

    public final com.example.wantudy.study.QStudy study;

    public final NumberPath<Long> studyDesiredTimeId = createNumber("studyDesiredTimeId", Long.class);

    public QStudyDesiredTime(String variable) {
        this(StudyDesiredTime.class, forVariable(variable), INITS);
    }

    public QStudyDesiredTime(Path<? extends StudyDesiredTime> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyDesiredTime(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyDesiredTime(PathMetadata metadata, PathInits inits) {
        this(StudyDesiredTime.class, metadata, inits);
    }

    public QStudyDesiredTime(Class<? extends StudyDesiredTime> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.desiredTime = inits.isInitialized("desiredTime") ? new QDesiredTime(forProperty("desiredTime")) : null;
        this.study = inits.isInitialized("study") ? new com.example.wantudy.study.QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

