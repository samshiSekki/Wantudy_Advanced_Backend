package com.example.wantudy.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRequiredInfo is a Querydsl query type for RequiredInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequiredInfo extends EntityPathBase<RequiredInfo> {

    private static final long serialVersionUID = -1932845715L;

    public static final QRequiredInfo requiredInfo = new QRequiredInfo("requiredInfo");

    public final NumberPath<Long> requiredInfoId = createNumber("requiredInfoId", Long.class);

    public final StringPath requiredInfoName = createString("requiredInfoName");

    public final ListPath<StudyRequiredInfo, QStudyRequiredInfo> studies = this.<StudyRequiredInfo, QStudyRequiredInfo>createList("studies", StudyRequiredInfo.class, QStudyRequiredInfo.class, PathInits.DIRECT2);

    public QRequiredInfo(String variable) {
        super(RequiredInfo.class, forVariable(variable));
    }

    public QRequiredInfo(Path<? extends RequiredInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequiredInfo(PathMetadata metadata) {
        super(RequiredInfo.class, metadata);
    }

}

