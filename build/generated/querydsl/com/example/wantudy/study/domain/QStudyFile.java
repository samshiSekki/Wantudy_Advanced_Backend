package com.example.wantudy.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyFile is a Querydsl query type for StudyFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyFile extends EntityPathBase<StudyFile> {

    private static final long serialVersionUID = -1921107355L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyFile studyFile = new QStudyFile("studyFile");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final StringPath s3FileName = createString("s3FileName");

    public final com.example.wantudy.study.QStudy study;

    public final NumberPath<Long> studyFileId = createNumber("studyFileId", Long.class);

    public QStudyFile(String variable) {
        this(StudyFile.class, forVariable(variable), INITS);
    }

    public QStudyFile(Path<? extends StudyFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyFile(PathMetadata metadata, PathInits inits) {
        this(StudyFile.class, metadata, inits);
    }

    public QStudyFile(Class<? extends StudyFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new com.example.wantudy.study.QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

