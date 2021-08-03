package com.team.delightserver.web.domain.academy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAcademy is a Querydsl query type for Academy
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAcademy extends EntityPathBase<Academy> {

    private static final long serialVersionUID = 2066607668L;

    public static final QAcademy academy = new QAcademy("academy");

    public final StringPath address = createString("address");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QAcademy(String variable) {
        super(Academy.class, forVariable(variable));
    }

    public QAcademy(Path<? extends Academy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAcademy(PathMetadata metadata) {
        super(Academy.class, metadata);
    }

}

