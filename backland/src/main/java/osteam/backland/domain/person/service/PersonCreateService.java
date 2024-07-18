package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.PersonOnly;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;
import osteam.backland.domain.person.repository.PersonOneToOneRepository;
import osteam.backland.domain.person.repository.PersonOnlyRepository;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.domain.phone.entity.PhoneOneToOne;

import java.util.ArrayList;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor // 빈 주입 받기위해, 롬복의 생성자 자동 생성 기능 사용
@Transactional
public class PersonCreateService {
    private final PersonUpdateService personUpdateService;
    private final PersonOnlyRepository personOnlyRepository;
    private final PersonOneToOneRepository personOneToOneRepository;
    private final PersonOneToManyRepository personOneToManyRepository;

    // 외부에 공개하는 용도구나. 이거 하나로 모든 관계형 매핑 테이블들 조작할 수 있게
    public PersonDTO createAll(PersonDTO personDTO) {
        one(personDTO);
        oneToOne(personDTO);
        oneToMany(personDTO);

        return PersonDTO.builder()
                .name(personDTO.getName())
                .phone(personDTO.getPhone())
                .build();
    }

    /**
     * person 하나로만 구성되어 있는 생성
     */
    private PersonDTO one(PersonDTO personDTO) {
        PersonOnly personOnly = PersonOnly.builder()
                .name(personDTO.getName())
                .phone(personDTO.getPhone())
                .build();

        personOnlyRepository.save(personOnly);
        return personDTO.toBuilder().build();
    }

    /**
     * Phone과 OneToOne 관계인 person 생성
     */
    private PersonDTO oneToOne(PersonDTO personDTO) {
        PersonOneToOne personOneToOne = PersonOneToOne.builder()
                .name(personDTO.getName())
                .phoneOneToOne(PhoneOneToOne.builder().phone(personDTO.getPhone()).build())
                .build();

        personOneToOneRepository.save(personOneToOne);
        return personDTO.toBuilder().build();
    }

    /**
     * Phone과 OneToMany 관계인 person 생성
     */
    private PersonDTO oneToMany(PersonDTO personDTO) {
        PersonOneToMany personOneToMany = PersonOneToMany.builder()
                .name(personDTO.getName())
                .phoneOneToMany(PhoneOneToMany.builder()
                        .phone(personDTO.getPhone())
                        .build())
                .build();
        personOneToManyRepository.save(personOneToMany);
        return personDTO.toBuilder().build();
    }
}
