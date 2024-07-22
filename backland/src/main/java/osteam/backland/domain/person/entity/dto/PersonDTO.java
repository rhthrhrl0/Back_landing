package osteam.backland.domain.person.entity.dto;

import lombok.*;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.PersonOnly;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED) // 빌더 패턴으로만 DTO를 만들 수 있도록 강제함. 동일 타입이라서 휴먼 에러 가능성 있음
@NoArgsConstructor
public class PersonDTO {
    private String name;
    private String phone;

    public static PersonDTO fromOnly(PersonOnly personOnly) {
        return PersonDTO.builder()
                .name(personOnly.getName())
                .phone(personOnly.getPhone())
                .build();
    }

    public static PersonDTO fromOneToOne(PersonOneToOne personOneToOne) {
        return PersonDTO.builder()
                .name(personOneToOne.getName())
                .phone(personOneToOne.getPhoneOneToOne().getPhone())
                .build();
    }
}
