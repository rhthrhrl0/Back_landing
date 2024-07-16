package osteam.backland.domain.person.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import osteam.backland.domain.phone.entity.PhoneOneToOne;
import osteam.backland.global.entity.PrimaryKeyEntity;


@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
public class PersonOneToOne extends PrimaryKeyEntity {

    private String name;

    @OneToOne(
            mappedBy = "person",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private PhoneOneToOne phoneOneToOne;

    public PersonOneToOne(String name, PhoneOneToOne phoneOneToOne) {
        this.name = name;
        updatePhoneNumber(phoneOneToOne);
    }

    public void updatePhoneNumber(PhoneOneToOne phoneOneToOne) {
        this.phoneOneToOne = phoneOneToOne;
        phoneOneToOne.updatePerson(this);
    }
}
