package osteam.backland.domain.person.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonOneToMany is a Querydsl query type for PersonOneToMany
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonOneToMany extends EntityPathBase<PersonOneToMany> {

    private static final long serialVersionUID = -1538132802L;

    public static final QPersonOneToMany personOneToMany = new QPersonOneToMany("personOneToMany");

    public final osteam.backland.global.entity.QPrimaryKeyEntity _super = new osteam.backland.global.entity.QPrimaryKeyEntity(this);

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final StringPath name = createString("name");

    public final ListPath<osteam.backland.domain.phone.entity.PhoneOneToMany, osteam.backland.domain.phone.entity.QPhoneOneToMany> phoneOneToMany = this.<osteam.backland.domain.phone.entity.PhoneOneToMany, osteam.backland.domain.phone.entity.QPhoneOneToMany>createList("phoneOneToMany", osteam.backland.domain.phone.entity.PhoneOneToMany.class, osteam.backland.domain.phone.entity.QPhoneOneToMany.class, PathInits.DIRECT2);

    public QPersonOneToMany(String variable) {
        super(PersonOneToMany.class, forVariable(variable));
    }

    public QPersonOneToMany(Path<? extends PersonOneToMany> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonOneToMany(PathMetadata metadata) {
        super(PersonOneToMany.class, metadata);
    }

}

