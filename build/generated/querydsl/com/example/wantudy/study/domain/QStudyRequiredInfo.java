package com.example.wantudy.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyRequiredInfo is a Querydsl query type for StudyRequiredInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyRequiredInfo extends EntityPathBase<StudyRequiredInfo> {

    private static final long serialVersionUID = 502950102L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyRequiredInfo studyRequiredInfo = new QStudyRequiredInfo("studyRequiredInfo");

    public final QRequiredInfo requiredInfo;

    public final com.example.wantudy.study.QStudy study;

    public final NumberPath<Long> studyRequiredInfoId = createNumber("studyRequiredInfoId", Long.class);

    public QStudyRequiredInfo(String variable) {
        this(StudyRequiredInfo.class, forVariable(variable), INITS);
    }

    public QStudyRequiredInfo(Path<? extends StudyRequiredInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyRequiredInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyRequiredInfo(PathMetadata metadata, PathInits inits) {
        this(StudyRequiredInfo.class, metadata, inits);
    }

    public QStudyRequiredInfo(Class<? extends StudyRequiredInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.requiredInfo = inits.isInitialized("requiredInfo") ? new QRequiredInfo(forProperty("requiredInfo")) : null;
        this.study = inits.isInitialized("study") ? new com.example.wantudy.study.QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

