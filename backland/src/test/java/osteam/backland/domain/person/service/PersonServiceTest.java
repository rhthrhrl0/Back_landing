package osteam.backland.domain.person.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.entity.dto.search.SearchByPhoneDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class PersonServiceTest {

    @Autowired
    private PersonCreateService personCreateService;

    @Autowired
    private PersonSearchService personSearchService;

    @Test
    @DisplayName("입력 인풋으로 들어온 휴대폰 번호가 처음인 경우 데이터베이스에 해당 이름으로 추가됨")
    void createPhone() {
        // given
        PersonDTO personDTO = PersonDTO.builder()
                .name("mendel")
                .phone("01012341234")
                .build();

        // when
        personCreateService.createAll(personDTO);
        SearchByPhoneDTO searchDTO = personSearchService.searchAllByPhone("01012341234");

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
                .phone("01012341234")
                .build();

        PersonDTO secondPersonDTO = PersonDTO.builder()
                .name("myeongjin")
                .phone("01012341234")
                .build();

        // when
        personCreateService.createAll(firstPersonDTO);
        SearchByPhoneDTO firstSearchDTO = personSearchService.searchAllByPhone("01012341234");

        personCreateService.createAll(secondPersonDTO);
        SearchByPhoneDTO secondSearchDTO = personSearchService.searchAllByPhone("01012341234");

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
}