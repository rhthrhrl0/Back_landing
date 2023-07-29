package osteam.backland.domain.phone.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhoneOneToMany is a Querydsl query type for PhoneOneToMany
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneOneToMany extends EntityPathBase<PhoneOneToMany> {

    private static final long serialVersionUID = -928258430L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhoneOneToMany phoneOneToMany = new QPhoneOneToMany("phoneOneToMany");

    public final osteam.backland.global.entity.QPrimaryKeyEntity _super = new osteam.backland.global.entity.QPrimaryKeyEntity(this);

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final osteam.backland.domain.person.entity.QPersonOneToMany personOneToMany;

    public final StringPath phone = createString("phone");

    public QPhoneOneToMany(String variable) {
        this(PhoneOneToMany.class, forVariable(variable), INITS);
    }

    public QPhoneOneToMany(Path<? extends PhoneOneToMany> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhoneOneToMany(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhoneOneToMany(PathMetadata metadata, PathInits inits) {
        this(PhoneOneToMany.class, metadata, inits);
    }

    public QPhoneOneToMany(Class<? extends PhoneOneToMany> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.personOneToMany = inits.isInitialized("personOneToMany") ? new osteam.backland.domain.person.entity.QPersonOneToMany(forProperty("personOneToMany")) : null;
    }

}

