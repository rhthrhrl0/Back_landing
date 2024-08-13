package osteam.backland.domain.person.repository.custom.onetomany;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import osteam.backland.domain.person.entity.PersonOneToMany;

import java.util.List;
import java.util.Optional;

import static osteam.backland.domain.person.entity.QPersonOneToMany.*;
import static osteam.backland.domain.phone.entity.QPhoneOneToMany.*;

@Slf4j
@RequiredArgsConstructor
public class PersonOneToManyRepositoryCustomImpl implements PersonOneToManyRepositoryCustom {
    private final EntityManager entityManager; // 프록시 객체임.
    private final JPAQueryFactory jpaQueryFactory; // 사전에 등록된 빈을 가져옴. 프록시 객체여서 새 엔티티 매니저를 가져와서 문제 없음.

    // QDSL로 만들어본 조회문
    // 그런데, 카티시안 곱으로 이렇게 다 케이스 만들고, 거기서 뽑는 한 개의 쿼리가 나을까. 아니면, 사람 먼저 조회하고, 그 사람 id기반으로 휴대폰 목록만 뽑는게 빠를까..
    @Override
    public Optional<List<PersonOneToMany>> searchByName(String name) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOneToMany)
                        .leftJoin(personOneToMany.phoneOneToMany, phoneOneToMany).fetchJoin()
                        .where(personOneToMany.name.eq(name))
                        .fetch()
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

    // 휴대폰 번호 기반으로 사람 엔티티 객체 JPA로 가져오고, 거기에서 이름 수정해서 적용하는거 너무 비효율적
    // QDSL로 한다.
    @Override
    public void updatePersonNameByPhone(String name, String phone) {
        jpaQueryFactory
                .update(personOneToMany)
                .set(personOneToMany.name, name)
                .where(personOneToMany.id.in(
                        JPAExpressions
                                .select(phoneOneToMany.personOneToMany.id)
                                .from(phoneOneToMany)
                                .where(phoneOneToMany.phone.eq(phone)))
                )
                .execute();

        log.debug("너 프록시 객체니? " + entityManager.getClass());
        entityManager.flush();
        entityManager.clear();
    }
}
