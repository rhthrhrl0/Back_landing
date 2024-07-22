package osteam.backland.domain.person.repository.custom.only;

import osteam.backland.domain.person.entity.PersonOnly;

import java.util.List;
import java.util.Optional;

public interface PersonOnlyCustomRepository {
    Optional<List<PersonOnly>> searchByName(String name);

    Optional<PersonOnly> searchByPhone(String phone);

    Optional<List<PersonOnly>> searchAll();
    void updatePersonNameByPhone(String name, String phone);
}
