package osteam.backland.domain.person.entity.dto;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED) // 빌더 패턴으로만 DTO를 만들 수 있도록 강제함. 동일 타입이라서 휴먼 에러 가능성 있음
@NoArgsConstructor
public class PersonDTO {
    private String name;
    private String phone;
}
