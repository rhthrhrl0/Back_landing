package osteam.backland.domain.person.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osteam.backland.domain.person.controller.request.PersonCreateRequest;
import osteam.backland.domain.person.controller.response.PersonResponse;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.service.PersonCreateService;
import osteam.backland.domain.person.service.PersonSearchService;
import osteam.backland.domain.person.service.PersonUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * PersonController
 * 등록 수정 검색 구현
 * <p>
 * 등록 - 입력된 이름과 전화번호를 personOnly, personOneToOne, personOneToMany에 저장
 * 수정 - 이미 등록된 전화번호가 입력될 경우 해당 전화번호의 소유주 이름을 변경
 * 검색 - 전체 출력, 이름으로 검색, 전화번호로 검색 구현, 검색은 전부 OneToMany 테이블로 진행.
 */
@Slf4j
@RestController // @Controller와 다르며, 메소드마다 리스폰스바디 어노테이션을 안달아도 된다. 뷰 필요없는 경우 씀
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonCreateService personCreateService;
    private final PersonUpdateService personUpdateService;
    private final PersonSearchService personSearchService;

    /**
     * 등록 기능
     * personRequest를 service에 그대로 넘겨주지 말 것.
     *
     * @param personCreateRequest
     * @return 성공 시 이름 반환
     */
    @PostMapping("/create")
    public ResponseEntity<String> person(@RequestBody @Valid PersonCreateRequest personCreateRequest) {
        PersonDTO personDTO = PersonDTO.builder()
                .name(personCreateRequest.getName())
                .phone(personCreateRequest.getPhone()).build();

        // BadRequest 로 처리하고 싶어서, ResponseEntity.of() 안씀.
        return Optional.ofNullable(personCreateService.createAll(personDTO))
                .map(pDto -> ResponseEntity.ok().body(pDto.getName()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * 전체 검색 기능
     */
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPeople() {
        return null;
    }

    /**
     * 이름으로 검색
     *
     * @param name
     */
    @GetMapping("/name")
    public ResponseEntity<List<PersonResponse>> getPeopleByName(String name) {
        return null;
    }

    /**
     * 번호로 검색
     *
     * @param phone
     */
    @GetMapping("/phone")
    public ResponseEntity<List<PersonResponse>> getPeopleByPhone(String phone) {
        return null;
    }
}
