package osteam.backland.domain.person.controller.response.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import osteam.backland.domain.person.controller.response.PersonOneToManyResponse;
import osteam.backland.domain.person.controller.response.PersonResponse;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class SearchByNameResponse {
    private List<PersonResponse> personOnly;
    private List<PersonResponse> personOneToOne;
    private List<PersonOneToManyResponse> personOneToMany;
}