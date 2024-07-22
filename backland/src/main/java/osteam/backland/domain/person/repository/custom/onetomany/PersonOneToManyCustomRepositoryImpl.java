package osteam.backland.domain.person.repository.custom.onetomany;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import osteam.backland.domain.person.entity.PersonOneToMany;

import java.util.List;
import java.util.Optional;

import static osteam.backland.domain.person.entity.QPersonOneToMany.*;
import static osteam.backland.domain.phone.entity.QPhoneOneToMany.*;

@RequiredArgsConstructor
public class PersonOneToManyCustomRepositoryImpl implements PersonOneToManyCustomRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory; // 사전에 등록된 빈을 가져옴. 프록시 객체여서 새 엔티티 매니저를 가져와서 문제 없음.

    // QDSL로 만들어본 조회문
    @Override
    public Optional<PersonOneToMany> searchByName(String name) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOneToMany)
                        .leftJoin(personOneToMany.phoneOneToMany, phoneOneToMany).fetchJoin()
                        .where(personOneToMany.name.eq(name))
                        .fetchOne()
        );
    }

    @Override
    public Optional<PersonOneToMany> searchByPhone(String phone) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOneToMany)
                        .leftJoin(personOneToMany.phoneOneToMany, phoneOneToMany).fetchJoin()
                        .where(personOneToMany.id.in(
                                JPAExpressions
                                        .select(phoneOneToMany.personOneToMany.id)
                                        .from(phoneOneToMany)
                                        .where(phoneOneToMany.phone.eq(phone))
                        ))
                        .fetchOne()
        );
    }

    @Override
    public Optional<List<PersonOneToMany>> searchAll() {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOneToMany)
                        .leftJoin(personOneToMany.phoneOneToMany, phoneOneToMany).fetchJoin() // 휴대폰이 없는 사람이라도 조회 목록에 포함시키고 싶어서 leftJoin 사용
                        .fetch()
        );
    }
}
