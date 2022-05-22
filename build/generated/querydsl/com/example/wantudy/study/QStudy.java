package com.example.wantudy.study;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = -2038350945L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy study = new QStudy("study");

    public final ListPath<com.example.wantudy.study.domain.StudyCategory, com.example.wantudy.study.domain.QStudyCategory> categories = this.<com.example.wantudy.study.domain.StudyCategory, com.example.wantudy.study.domain.QStudyCategory>createList("categories", com.example.wantudy.study.domain.StudyCategory.class, com.example.wantudy.study.domain.QStudyCategory.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final SimplePath<Number> currentNum = createSimple("currentNum", Number.class);

    public final DatePath<java.time.LocalDate> deadline = createDate("deadline", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final ListPath<com.example.wantudy.study.domain.StudyDesiredTime, com.example.wantudy.study.domain.QStudyDesiredTime> desiredTime = this.<com.example.wantudy.study.domain.StudyDesiredTime, com.example.wantudy.study.domain.QStudyDesiredTime>createList("desiredTime", com.example.wantudy.study.domain.StudyDesiredTime.class, com.example.wantudy.study.domain.QStudyDesiredTime.class, PathInits.DIRECT2);

    public final StringPath fixedStudySchedule = createString("fixedStudySchedule");

    public final StringPath format = createString("format");

    public final StringPath level = createString("level");

    public final SimplePath<Number> likeNum = createSimple("likeNum", Number.class);

    public final StringPath location = createString("location");

    public final SimplePath<Number> peopleNum = createSimple("peopleNum", Number.class);

    public final StringPath period = createString("period");

    public final ListPath<com.example.wantudy.study.domain.StudyRequiredInfo, com.example.wantudy.study.domain.QStudyRequiredInfo> requiredInfo = this.<com.example.wantudy.study.domain.StudyRequiredInfo, com.example.wantudy.study.domain.QStudyRequiredInfo>createList("requiredInfo", com.example.wantudy.study.domain.StudyRequiredInfo.class, com.example.wantudy.study.domain.QStudyRequiredInfo.class, PathInits.DIRECT2);

    public final ListPath<com.example.wantudy.study.domain.StudyFile, com.example.wantudy.study.domain.QStudyFile> studyFiles = this.<com.example.wantudy.study.domain.StudyFile, com.example.wantudy.study.domain.QStudyFile>createList("studyFiles", com.example.wantudy.study.domain.StudyFile.class, com.example.wantudy.study.domain.QStudyFile.class, PathInits.DIRECT2);

    public final NumberPath<Long> studyId = createNumber("studyId", Long.class);

    public final StringPath studyName = createString("studyName");

    public final EnumPath<com.example.wantudy.study.domain.StudyStatus> studyStatus = createEnum("studyStatus", com.example.wantudy.study.domain.StudyStatus.class);

    public final com.example.wantudy.oauth.QUser user;

    public QStudy(String variable) {
        this(Study.class, forVariable(variable), INITS);
    }

    public QStudy(Path<? extends Study> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudy(PathMetadata metadata, PathInits inits) {
        this(Study.class, metadata, inits);
    }

    public QStudy(Class<? extends Study> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.wantudy.oauth.QUser(forProperty("user")) : null;
    }

}

