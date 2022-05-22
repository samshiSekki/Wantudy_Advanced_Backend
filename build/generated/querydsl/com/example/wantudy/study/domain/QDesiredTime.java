package com.example.wantudy.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDesiredTime is a Querydsl query type for DesiredTime
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDesiredTime extends EntityPathBase<DesiredTime> {

    private static final long serialVersionUID = -1102350329L;

    public static final QDesiredTime desiredTime1 = new QDesiredTime("desiredTime1");

    public final StringPath desiredTime = createString("desiredTime");

    public final NumberPath<Long> desiredTimeId = createNumber("desiredTimeId", Long.class);

    public final ListPath<StudyDesiredTime, QStudyDesiredTime> studies = this.<StudyDesiredTime, QStudyDesiredTime>createList("studies", StudyDesiredTime.class, QStudyDesiredTime.class, PathInits.DIRECT2);

    public QDesiredTime(String variable) {
        super(DesiredTime.class, forVariable(variable));
    }

    public QDesiredTime(Path<? extends DesiredTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDesiredTime(PathMetadata metadata) {
        super(DesiredTime.class, metadata);
    }

}

