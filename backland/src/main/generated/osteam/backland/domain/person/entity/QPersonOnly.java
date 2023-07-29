package osteam.backland.domain.person.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonOnly is a Querydsl query type for PersonOnly
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonOnly extends EntityPathBase<PersonOnly> {

    private static final long serialVersionUID = -1996872274L;

    public static final QPersonOnly personOnly = new QPersonOnly("personOnly");

    public final osteam.backland.global.entity.QPrimaryKeyEntity _super = new osteam.backland.global.entity.QPrimaryKeyEntity(this);

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public QPersonOnly(String variable) {
        super(PersonOnly.class, forVariable(variable));
    }

    public QPersonOnly(Path<? extends PersonOnly> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonOnly(PathMetadata metadata) {
        super(PersonOnly.class, metadata);
    }

}

