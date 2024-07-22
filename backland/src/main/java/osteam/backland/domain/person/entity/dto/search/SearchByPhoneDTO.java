package osteam.backland.domain.person.entity.dto.search;

import lombok.*;
import osteam.backland.domain.person.controller.response.search.SearchByPhoneResponse;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.entity.dto.PersonOneToManyDTO;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class SearchByPhoneDTO {
    private PersonDTO personOnly;
    private PersonDTO personOneToOne;
    private PersonOneToManyDTO personOneToMany;

    public SearchByPhoneResponse toResponse() {
        return SearchByPhoneResponse.builder()
                .personOnly(personOnly.toResponse())
                .personOneToOne(personOneToOne.toResponse())
                .personOneToMany(personOneToMany.toResponse())
                .build();
    }
}
