package osteam.backland.domain.person.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import osteam.backland.global.entity.PrimaryKeyEntity;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PersonOnly extends PrimaryKeyEntity {
    private String name;
    private String phone;
}
