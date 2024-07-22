package osteam.backland.domain.person.entity.dto.search;

import lombok.*;
import osteam.backland.domain.person.controller.response.search.SearchByNameResponse;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.entity.dto.PersonOneToManyDTO;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class SearchByNameDTO {
    private List<PersonDTO> personOnly;
    private List<PersonDTO> personOneToOne;
    private List<PersonOneToManyDTO> personOneToMany;

    public SearchByNameResponse toResponse() {
        return SearchByNameResponse.builder()
                .personOnly(personOnly.stream()
                        .map(PersonDTO::toResponse)
                        .collect(Collectors.toList()))
                .personOneToOne(personOneToOne.stream()
                        .map(PersonDTO::toResponse)
                        .collect(Collectors.toList()))
                .personOneToMany(personOneToMany.stream()
                        .map(PersonOneToManyDTO::toResponse)
                        .collect(Collectors.toList()))
                .build();
    }
}
