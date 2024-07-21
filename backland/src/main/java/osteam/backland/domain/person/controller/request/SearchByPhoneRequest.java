package osteam.backland.domain.person.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import osteam.backland.global.constraint.PhoneNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchByPhoneRequest {
    @PhoneNumber
    private String phone;
}
