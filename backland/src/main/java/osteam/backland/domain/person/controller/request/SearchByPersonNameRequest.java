package osteam.backland.domain.person.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchByPersonNameRequest {
    @Size(min = 1, max = 20, message = "이름은 1자 이상 20자 이하여야 합니다.")
    @NotBlank
    private String name;
}
