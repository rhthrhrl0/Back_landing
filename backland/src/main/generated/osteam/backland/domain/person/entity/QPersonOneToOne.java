package osteam.backland.domain.person.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonOneToOne is a Querydsl query type for PersonOneToOne
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonOneToOne extends EntityPathBase<PersonOneToOne> {

    private static final long serialVersionUID = -2127824857L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonOneToOne personOneToOne = new QPersonOneToOne("personOneToOne");

    public final osteam.backland.global.entity.QPrimaryKeyEntity _super = new osteam.backland.global.entity.QPrimaryKeyEntity(this);

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final StringPath name = createString("name");

    public final osteam.backland.domain.phone.entity.QPhoneOneToOne phoneOneToOne;

    public QPersonOneToOne(String variable) {
        this(PersonOneToOne.class, forVariable(variable), INITS);
    }

    public QPersonOneToOne(Path<? extends PersonOneToOne> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersonOneToOne(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersonOneToOne(PathMetadata metadata, PathInits inits) {
        this(PersonOneToOne.class, metadata, inits);
    }

    public QPersonOneToOne(Class<? extends PersonOneToOne> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.phoneOneToOne = inits.isInitialized("phoneOneToOne") ? new osteam.backland.domain.phone.entity.QPhoneOneToOne(forProperty("phoneOneToOne"), inits.get("phoneOneToOne")) : null;
    }

}

