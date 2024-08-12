package osteam.backland.domain.person.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import osteam.backland.global.valid.PhoneNumber;


@Data
@AllArgsConstructor
public class PersonCreateRequest {
    @NotNull(message = "이름은 NULL 일 수 없습니다.")
    @Size(min = 1, max = 20, message = "이름은 1자 이상 20자 이하여야 합니다.")
    @NotBlank
    private String name;

    @NotNull(message = "폰 번호는 NULL 일 수 없습니다.")
    @PhoneNumber
    private String phone;
}
