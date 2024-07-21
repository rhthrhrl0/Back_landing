package osteam.backland.domain.person.entity.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import osteam.backland.domain.person.controller.response.PersonResponse;
import osteam.backland.domain.person.entity.PersonOneToMany;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonOneToManyDTO {
    private String name;
    private List<String> phones;

    public List<PersonResponse> toResponse() {
        return phones.stream().
                map(phone -> PersonResponse.builder()
                        .name(name)
                        .phone(phone)
                        .build())
                .collect(Collectors.toList());
    }

    public static PersonOneToManyDTO toDto(PersonOneToMany personOneToMany) {
        return PersonOneToManyDTO.builder()
                .name(personOneToMany.getName())
                .phones(personOneToMany.getPhones())
                .build();
    }
}
