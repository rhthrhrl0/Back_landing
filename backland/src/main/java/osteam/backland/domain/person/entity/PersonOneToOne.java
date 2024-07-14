package osteam.backland.domain.person.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import osteam.backland.domain.phone.entity.PhoneOneToOne;
import osteam.backland.global.entity.PrimaryKeyEntity;


@Entity
@Setter
@Getter
public class PersonOneToOne extends PrimaryKeyEntity {

    private String name;

    @OneToOne(
            mappedBy = "person",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private PhoneOneToOne phoneOneToOne;
}
