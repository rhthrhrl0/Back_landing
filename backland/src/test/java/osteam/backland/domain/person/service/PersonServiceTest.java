package osteam.backland.domain.person.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.entity.dto.search.SearchByNameDTO;
import osteam.backland.domain.person.entity.dto.search.SearchByPhoneDTO;
import osteam.backland.global.exception.model.CustomException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class PersonServiceTest {

    @Autowired
    private PersonCreateService personCreateService;

    @Autowired
    private PersonSearchService personSearchService;

    @BeforeEach
    public void before() {
        PersonDTO person1 = PersonDTO.builder()
                .name("홍길동")
                .phone("010-1111-2222")
                .build();
        PersonDTO person2 = PersonDTO.builder()
                .name("홍길동")
                .phone("010-3333-4444")
                .build();
        PersonDTO person3 = PersonDTO.builder()
                .name("김철수")
                .phone("010-5555-6666")
                .build();

        personCreateService.createAll(person1);
        personCreateService.createAll(person2);
        personCreateService.createAll(person3);
    }

    @Test
    @DisplayName("입력 인풋으로 들어온 휴대폰 번호가 처음인 경우 데이터베이스에 해당 이름으로 추가됨")
    void createPhone() {
        // given
        PersonDTO personDTO = PersonDTO.builder()
                .name("mendel")
                .phone("010-1234-1234")
                .build();

        // when
        personCreateService.createAll(personDTO);
        SearchByPhoneDTO searchDTO = personSearchService.searchAllByPhone("010-1234-1234");

        // then
        assertAll(
                () -> assertThat(searchDTO.getPersonOnly().getName()).isEqualTo("mendel"),
                () -> assertThat(searchDTO.getPersonOneToOne().getName()).isEqualTo("mendel"),
                () -> assertThat(searchDTO.getPersonOneToMany().getName()).isEqualTo("mendel")
        );
    }

    @Test
    @DisplayName("입력 인풋으로 들어온 휴대폰 번호가 처음이 아닌 경우 주인 이름을 변경")
    void changePersonName() {
        // given
        PersonDTO firstPersonDTO = PersonDTO.builder()
                .name("mendel")
                .phone("010-1234-1234")
                .build();

        PersonDTO secondPersonDTO = PersonDTO.builder()
                .name("myeongjin")
                .phone("010-1234-1234")
                .build();

        // when
        personCreateService.createAll(firstPersonDTO);
        SearchByPhoneDTO firstSearchDTO = personSearchService.searchAllByPhone("010-1234-1234");

        personCreateService.createAll(secondPersonDTO);
        SearchByPhoneDTO secondSearchDTO = personSearchService.searchAllByPhone("010-1234-1234");

        // then
        assertAll(
                () -> assertAll(
                        () -> assertThat(firstSearchDTO.getPersonOnly().getName()).isEqualTo("mendel"),
                        () -> assertThat(firstSearchDTO.getPersonOneToOne().getName()).isEqualTo("mendel"),
                        () -> assertThat(firstSearchDTO.getPersonOneToMany().getName()).isEqualTo("mendel")
                ),
                () -> assertAll(
                        () -> assertThat(secondSearchDTO.getPersonOnly().getName()).isEqualTo("myeongjin"),
                        () -> assertThat(secondSearchDTO.getPersonOneToOne().getName()).isEqualTo("myeongjin"),
                        () -> assertThat(secondSearchDTO.getPersonOneToMany().getName()).isEqualTo("myeongjin")
                )
        );
    }

    @Test
    @DisplayName("이름이 홍길동인 유저 모두 조회시 결과 2")
    void searchPersonName_홍길동() {
        // when
        SearchByNameDTO searchByNameDTO = personSearchService.searchAllByName("홍길동");

        assertAll(
                () -> assertThat(searchByNameDTO.getPersonOnly().size()).isEqualTo(2),
                () -> assertThat(searchByNameDTO.getPersonOneToOne().size()).isEqualTo(2),
                () -> assertThat(searchByNameDTO.getPersonOneToMany().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("이름이 김철수인 유저 모두 조회시 결과 1")
    void searchPersonName_김철수() {
        // when
        SearchByNameDTO searchByNameDTO = personSearchService.searchAllByName("김철수");

        assertAll(
                () -> assertThat(searchByNameDTO.getPersonOnly().size()).isEqualTo(1),
                () -> assertThat(searchByNameDTO.getPersonOneToOne().size()).isEqualTo(1),
                () -> assertThat(searchByNameDTO.getPersonOneToMany().size()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("이름이 이몽룡인 유저 모두 조회시 결과 0")
    void searchPersonName_이몽룡() {
        // when
        SearchByNameDTO searchByNameDTO = personSearchService.searchAllByName("이몽룡");

        assertAll(
                () -> assertThat(searchByNameDTO.getPersonOnly().size()).isEqualTo(0),
                () -> assertThat(searchByNameDTO.getPersonOneToOne().size()).isEqualTo(0),
                () -> assertThat(searchByNameDTO.getPersonOneToMany().size()).isEqualTo(0)
        );
    }


    @Test
    @DisplayName("폰 번호가 010-1111-2222인 사람 조회시 이름이 홍길동")
    void successSearchPersonPhone() {
        // when
        SearchByPhoneDTO searchByPhoneDTO = personSearchService.searchAllByPhone("010-1111-2222");

        assertAll(
                () -> assertThat(searchByPhoneDTO.getPersonOnly().getName()).isEqualTo("홍길동"),
                () -> assertThat(searchByPhoneDTO.getPersonOneToOne().getName()).isEqualTo("홍길동"),
                () -> assertThat(searchByPhoneDTO.getPersonOneToMany().getName()).isEqualTo("홍길동")
        );
    }

    @Test
    @DisplayName("폰 번호가 010-8888-9999인 사람 조회시 실패")
    void failedSearchPersonPhone() {
        // when, then
        CustomException customException = assertThrows(CustomException.class, () -> {
            personSearchService.searchAllByPhone("010-8888-9999");
        });

        assertThat(customException.getErrorMessage()).isEqualTo("phone번호가 " + "010-8888-9999" + "인 사람을 찾을 수 없습니다.");
    }
}