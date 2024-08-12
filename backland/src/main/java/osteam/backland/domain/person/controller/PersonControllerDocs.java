package osteam.backland.domain.person.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;
import osteam.backland.domain.person.controller.request.PersonCreateRequest;
import osteam.backland.domain.person.controller.request.SearchByPersonNameRequest;
import osteam.backland.domain.person.controller.request.SearchByPhoneRequest;
import osteam.backland.domain.person.controller.response.search.SearchAllResponse;
import osteam.backland.domain.person.controller.response.search.SearchByNameResponse;
import osteam.backland.domain.person.controller.response.search.SearchByPhoneResponse;
import osteam.backland.global.exception.ExceptionResponse;

public interface PersonControllerDocs {
    @Tag(name = "유저 추가/수정 API")
    ResponseEntity<String> person(@RequestBody @Valid PersonCreateRequest request);

    @Tag(name = "유저 정보 조회 API")
    @Operation(summary = "전체 조회", description = "Only, OneToOne, OneToMany 모두 조회해서 하나로 묶어 응답")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공 응답", content = @Content(schema = @Schema(implementation = SearchAllResponse.class))),
        @ApiResponse(responseCode = "404", description = "실패", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    ResponseEntity<SearchAllResponse> getPeople();

    @Operation(summary = "이름 기반으로 조회", description = "Only, OneToOne, OneToMany 모두 조회해서 하나로 묶어 응답. 사람의 이름은 유니크하지 않으므로 각각 배열 형태로 전달될 것임.")
    @Tag(name = "유저 정보 조회 API")
    ResponseEntity<SearchByNameResponse> getPeopleByName(@RequestBody @Valid SearchByPersonNameRequest searchByPersonNameRequest);

    @Operation(summary = "폰 번호 기반으로 조회", description = "Only, OneToOne, OneToMany 모두 조회해서 하나로 묶어 응답. 휴대폰 번호는 유니크함. 한 객체씩만 전달될 것임")
    @Tag(name = "유저 정보 조회 API")
    ResponseEntity<SearchByPhoneResponse> getPeopleByPhone(@RequestBody @Valid SearchByPhoneRequest searchByPhoneRequest);
}
