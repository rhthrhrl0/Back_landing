package osteam.backland.domain.person.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.global.entity.PrimaryKeyEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
public class PersonOneToMany extends PrimaryKeyEntity {

    private String name;

    @Singular("phoneOneToMany")
    @OneToMany(
            mappedBy = "personOneToMany",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<PhoneOneToMany> phoneOneToMany = new ArrayList<>();

    // 빌더에 의해 실행될 생성자
    PersonOneToMany(String name, List<PhoneOneToMany> phoneOneToMany) {
        this.name = name;
        this.phoneOneToMany = new ArrayList<>();
        for (PhoneOneToMany phone : phoneOneToMany) {
            addPhone(phone);
        }
    }

    public void addPhone(PhoneOneToMany phoneOneToMany) {
        this.phoneOneToMany.add(phoneOneToMany);
        phoneOneToMany.updatePerson(this); // 결국엔 연관관계 주인쪽에서 외래키와 매핑된 객체를 수정해야 적용되기 때문
    }

    public List<String> getPhones() {
        return phoneOneToMany.stream()
                .map(PhoneOneToMany::getPhone)
                .collect(Collectors.toList());
    }
}
