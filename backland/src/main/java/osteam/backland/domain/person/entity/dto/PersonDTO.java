package osteam.backland.domain.person.entity.dto;

import lombok.*;
import osteam.backland.domain.person.controller.request.PersonCreateRequest;
import osteam.backland.domain.person.controller.response.PersonResponse;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.PersonOnly;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED) // 빌더 패턴으로만 DTO를 만들 수 있도록 강제함. 동일 타입이라서 휴먼 에러 가능성 있음
@NoArgsConstructor
public class PersonDTO {
    private String name;
    private String phone;

    public static PersonDTO from(PersonOnly personOnly) {
        return PersonDTO.builder()
                .name(personOnly.getName())
                .phone(personOnly.getPhone())
                .build();
    }

    public static PersonDTO from(PersonOneToOne personOneToOne) {
        return PersonDTO.builder()
                .name(personOneToOne.getName())
                .phone(personOneToOne.getPhoneOneToOne().getPhone())
                .build();
    }

    public static PersonDTO fromRequest(PersonCreateRequest personCreateRequest) {
        return PersonDTO.builder()
                .name(personCreateRequest.getName())
                .phone(personCreateRequest.getPhone())
                .build();
    }

    public PersonResponse toResponse() {
        return PersonResponse.builder()
                .name(name)
                .phone(phone)
                .build();
    }
}
