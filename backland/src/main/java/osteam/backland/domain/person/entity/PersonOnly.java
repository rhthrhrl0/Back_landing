package osteam.backland.domain.person.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import osteam.backland.global.entity.PrimaryKeyEntity;

@Entity
@Getter
@Setter
public class PersonOnly extends PrimaryKeyEntity {
    private String name;
    private String phone;

}
