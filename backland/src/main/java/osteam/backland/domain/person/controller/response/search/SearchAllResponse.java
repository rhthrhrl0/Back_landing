package osteam.backland.domain.person.controller.response.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import osteam.backland.domain.person.controller.response.PersonOneToManyResponse;
import osteam.backland.domain.person.controller.response.PersonResponse;

import java.util.List;

@Data
@Builder(toBuilder = true)
@Schema(description = "모든 유저와 그 유저들이 가진 휴대폰 목록 조회 DTO")
@AllArgsConstructor
public class SearchAllResponse {
    private List<PersonResponse> personOnly;
    private List<PersonResponse> personOneToOne;
    private List<PersonOneToManyResponse> personOneToMany;
}