package osteam.backland.domain.person.repository.custom.only;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import osteam.backland.domain.person.entity.PersonOnly;

import java.util.List;
import java.util.Optional;

import static osteam.backland.domain.person.entity.QPersonOnly.personOnly;

@Slf4j
@RequiredArgsConstructor
public class PersonOnlyRepositoryCustomImpl implements PersonOnlyRepositoryCustom {
    private final EntityManager entityManager; // 프록시 객체임.
    private final JPAQueryFactory jpaQueryFactory; // 사전에 등록된 빈을 가져옴. 프록시 객체여서 새 엔티티 매니저를 가져와서 문제 없음.

    // QDSL로 만들어본 조회문
    // 그런데, 카티시안 곱으로 이렇게 다 케이스 만들고, 거기서 뽑는 한 개의 쿼리가 나을까. 아니면, 사람 먼저 조회하고, 그 사람 id기반으로 휴대폰 목록만 뽑는게 빠를까..
    @Override
    public Optional<List<PersonOnly>> searchByName(String name) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOnly)
                        .where(personOnly.name.eq(name))
                        .fetch()
        );
    }

    @Override
    public Optional<PersonOnly> searchByPhone(String phone) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOnly)
                        .where(personOnly.phone.eq(phone))
                        .fetchOne()
        );
    }

    @Override
    public Optional<List<PersonOnly>> searchAll() {
        return Optional.of(
                jpaQueryFactory
                        .selectFrom(personOnly)
                        .fetch()
        );
    }

    // 휴대폰 번호 기반으로 사람 엔티티 객체 JPA로 가져오고, 거기에서 이름 수정해서 적용하는거 너무 비효율적
    // QDSL로 한다.
    @Override
    public void updatePersonNameByPhone(String name, String phone) {
        jpaQueryFactory
                .update(personOnly)
                .set(personOnly.name, name)
                .where(personOnly.phone.eq(phone))
                .execute();

        log.debug("너 프록시 객체니? " + entityManager.getClass());
        entityManager.flush();
        entityManager.clear();
    }
}
