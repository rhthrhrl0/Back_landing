package osteam.backland.domain.person.repository.custom.onetoone;

import osteam.backland.domain.person.entity.PersonOneToOne;

import java.util.List;
import java.util.Optional;

public interface PersonOneToOneRepositoryCustom {
    Optional<List<PersonOneToOne>> searchByName(String name);

    Optional<PersonOneToOne> searchByPhone(String phone);

    Optional<List<PersonOneToOne>> searchAll();
    void updatePersonNameByPhone(String name, String phone);
}
