package osteam.backland.domain.person.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osteam.backland.domain.person.controller.request.PersonCreateRequest;
import osteam.backland.domain.person.controller.request.SearchByPersonNameRequest;
import osteam.backland.domain.person.controller.request.SearchByPhoneRequest;
import osteam.backland.domain.person.controller.response.search.SearchAllResponse;
import osteam.backland.domain.person.controller.response.search.SearchByNameResponse;
import osteam.backland.domain.person.controller.response.search.SearchByPhoneResponse;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.service.PersonCreateService;
import osteam.backland.domain.person.service.PersonSearchService;

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
public class PersonController implements PersonControllerDocs {

    private final PersonCreateService personCreateService;
    private final PersonSearchService personSearchService;

    /**
     * 등록 기능
     * personRequest를 service에 그대로 넘겨주지 말 것.
     *
     * @param request
     * @return 성공 시 이름 반환
     */
    @PostMapping("/create")
    public ResponseEntity<String> person(@RequestBody @Valid PersonCreateRequest request) {
        String response = personCreateService.createAll(PersonDTO.from(request)).getName();
        return ResponseEntity.ok().body(response);
    }

    /**
     * 전체 검색 기능
     */
    @GetMapping
    public ResponseEntity<SearchAllResponse> getPeople() {
        SearchAllResponse response = personSearchService.searchAll().toResponse();
        return ResponseEntity.ok(response);
    }

    /**
     * 이름으로 검색
     *
     * @param searchByPersonNameRequest
     */
    @GetMapping("/name")
    public ResponseEntity<SearchByNameResponse> getPeopleByName(@RequestBody @Valid SearchByPersonNameRequest searchByPersonNameRequest) {
        SearchByNameResponse response = personSearchService.searchAllByName(searchByPersonNameRequest.getName()).toResponse();
        return ResponseEntity.ok(response);
    }

    /**
     * 번호로 검색
     *
     * @param searchByPhoneRequest
     */
    @GetMapping("/phone")
    public ResponseEntity<SearchByPhoneResponse> getPeopleByPhone(@RequestBody @Valid SearchByPhoneRequest searchByPhoneRequest) {
        SearchByPhoneResponse response = personSearchService.searchAllByPhone(searchByPhoneRequest.getPhone()).toResponse();
        return ResponseEntity.ok(response);
    }
}
