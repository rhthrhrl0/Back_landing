package osteam.backland.domain.person.controller.response.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import osteam.backland.domain.person.controller.response.PersonOneToManyResponse;
import osteam.backland.domain.person.controller.response.PersonResponse;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class SearchByPhoneResponse {
    private PersonResponse personOnly;
    private PersonResponse personOneToOne;
    private PersonOneToManyResponse personOneToMany;
}