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
import osteam.backland.domain.phone.repository.PhoneOneToManyRepository;

@Service
@Slf4j
@RequiredArgsConstructor // 빈 주입 받기위해, 롬복의 생성자 자동 생성 기능 사용
@Transactional
public class PersonCreateService {
    private final PersonUpdateService personUpdateService;
    private final PersonOnlyRepository personOnlyRepository;
    private final PersonOneToOneRepository personOneToOneRepository;
    private final PersonOneToManyRepository personOneToManyRepository;
    private final PhoneOneToManyRepository phoneOneToManyRepository;

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
        phoneOneToManyRepository.findByPhone(personDTO.getPhone())
                .ifPresentOrElse((phoneOneToMany) -> {
                    personOneToManyRepository.findByName(personDTO.getName())
                            .ifPresentOrElse((personOneToMany) -> {
                                System.out.println("폰 정보와 사람정보 둘 다 있음. 폰의 주인 정보를 변경");
                                personOneToMany.addPhone(phoneOneToMany);
                            }, () -> {
                                System.out.println("폰 정보 있지만 사람정보는 없음. 사람 새로 추가하고 폰의 주인 정보를 변경");
                                PersonOneToMany personOneToMany = PersonOneToMany.builder()
                                        .name(personDTO.getName())
                                        .phoneOneToMany(phoneOneToMany)
                                        .build();
                                personOneToManyRepository.save(personOneToMany);
                            });
                }, () -> { // 폰 조회 없는 경우. 신규 등록 폰 번호
                    personOneToManyRepository.findByName(personDTO.getName())
                            .ifPresentOrElse(personOneToMany -> {
                                System.out.println("폰 정보 없지만 사람정보는 있음. 폰 새로 정보 추가");
                                personOneToMany.addPhone(PhoneOneToMany.builder()
                                        .phone(personDTO.getPhone())
                                        .build());
                            }, () -> {
                                System.out.println("폰 정보 없고 사람정보도 없음. 사람과 폰 둘 다 추가");
                                PersonOneToMany personOneToMany = PersonOneToMany.builder()
                                        .name(personDTO.getName())
                                        .phoneOneToMany(PhoneOneToMany.builder()
                                                .phone(personDTO.getPhone())
                                                .build())
                                        .build();
                                personOneToManyRepository.save(personOneToMany);
                            });
                });
        return personDTO.toBuilder().build();
    }
}
