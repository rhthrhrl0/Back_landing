package osteam.backland.domain.phone.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhoneOneToOne is a Querydsl query type for PhoneOneToOne
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneOneToOne extends EntityPathBase<PhoneOneToOne> {

    private static final long serialVersionUID = -861225501L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhoneOneToOne phoneOneToOne = new QPhoneOneToOne("phoneOneToOne");

    public final osteam.backland.global.entity.QPrimaryKeyEntity _super = new osteam.backland.global.entity.QPrimaryKeyEntity(this);

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final osteam.backland.domain.person.entity.QPersonOneToOne person;

    public final StringPath phone = createString("phone");

    public QPhoneOneToOne(String variable) {
        this(PhoneOneToOne.class, forVariable(variable), INITS);
    }

    public QPhoneOneToOne(Path<? extends PhoneOneToOne> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhoneOneToOne(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhoneOneToOne(PathMetadata metadata, PathInits inits) {
        this(PhoneOneToOne.class, metadata, inits);
    }

    public QPhoneOneToOne(Class<? extends PhoneOneToOne> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.person = inits.isInitialized("person") ? new osteam.backland.domain.person.entity.QPersonOneToOne(forProperty("person"), inits.get("person")) : null;
    }

}

