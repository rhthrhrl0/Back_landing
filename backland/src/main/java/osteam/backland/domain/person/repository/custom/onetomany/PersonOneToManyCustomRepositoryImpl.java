package osteam.backland.domain.person.repository.custom.onetomany;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import osteam.backland.domain.person.entity.PersonOneToMany;

import java.util.Optional;

import static osteam.backland.domain.person.entity.QPersonOneToMany.*;
import static osteam.backland.domain.phone.entity.QPhoneOneToMany.*;

@RequiredArgsConstructor
public class PersonOneToManyCustomRepositoryImpl implements PersonOneToManyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory; // 사전에 등록된 빈을 가져옴.
    private final EntityManager em; // 스프링 부트 3.2 이상부터 프록시 빈이 적용되어서 이렇게 해도 문제 없다고 함.

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
}
