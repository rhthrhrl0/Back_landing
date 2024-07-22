package osteam.backland.domain.person.entity.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import osteam.backland.domain.person.controller.response.PersonOneToManyResponse;
import osteam.backland.domain.person.entity.PersonOneToMany;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonOneToManyDTO {
    private String name;
    private List<String> phones;

    public PersonOneToManyResponse toResponse() {
        return PersonOneToManyResponse.builder()
                .name(name)
                .phones(phones)
                .build();
    }

    public static PersonOneToManyDTO fromOneToMany(PersonOneToMany personOneToMany) {
        return PersonOneToManyDTO.builder()
                .name(personOneToMany.getName())
                .phones(personOneToMany.getPhones())
                .build();
    }
}
