package osteam.backland.domain.person.controller.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class PersonResponseOneToMany {
    @NonNull
    private String name;

    @NonNull
    private List<String> phones;
}
