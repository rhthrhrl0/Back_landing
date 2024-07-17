package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.repository.PersonOneToOneRepository;
import osteam.backland.domain.phone.entity.PhoneOneToOne;

@Service
@Slf4j
@RequiredArgsConstructor // 빈 주입 받기위해, 롬복의 생성자 자동 생성 기능 사용
@Transactional
public class PersonCreateService {

    private final PersonOneToOneRepository personOneToOneRepository;

    // 외부에 공개하는 용도구나. 이거 하나로 모든 관계형 매핑 테이블들 조작할 수 있게
    public PersonDTO createAll(PersonDTO personDTO) {
        createOneToOne(personDTO);
        // 다른 녀석들도 들어와야 함.

        return PersonDTO.builder()
                .name(personDTO.getName())
                .phone(personDTO.getPhone())
                .build();
    }

    private PersonDTO createOneToOne(PersonDTO personDTO) {
        PersonOneToOne personOneToOne = PersonOneToOne.builder()
                .name(personDTO.getName())
                .phoneOneToOne(PhoneOneToOne.builder().phone(personDTO.getPhone()).build())
                .build();

        personOneToOneRepository.save(personOneToOne);
        return personDTO;
    }

    /**
     * Phone과 OneToOne 관계인 person 생성
     */
    public PersonDTO oneToOne(String name, String phone) {
        return null;
    }

    /**
     * Phone과 OneToMany 관계인 person 생성
     */
    public PersonDTO oneToMany(String name, String phone) {
        return null;
    }

    /**
     * person 하나로만 구성되어 있는 생성
     */
    public PersonDTO one(String name, String phone) {
        return null;
    }
}
