package osteam.backland.domain.person.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PersonCreateRequest {
    private String name;
    private String phone;
}
