package osteam.backland.domain.phone.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.global.entity.PrimaryKeyEntity;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
public class PhoneOneToMany extends PrimaryKeyEntity {

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "fk_person_id",
            referencedColumnName = "id"
    )
    private PersonOneToMany personOneToMany;

    // 빌더에 의해 호출될 것임.
    PhoneOneToMany(String phone, PersonOneToMany personOneToMany) {
        this.phone = phone;
        updatePerson(personOneToMany);
    }

    public void updatePerson(PersonOneToMany personOneToMany) {
        this.personOneToMany = personOneToMany;
    }

    @Override
    public String toString() {
        return phone;
    }
}
