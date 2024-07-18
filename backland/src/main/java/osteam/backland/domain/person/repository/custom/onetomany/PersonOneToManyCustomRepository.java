package osteam.backland.domain.person.repository.custom.onetomany;

import osteam.backland.domain.person.entity.PersonOneToMany;

import java.util.Optional;

public interface PersonOneToManyCustomRepository {
    Optional<PersonOneToMany> searchByName(String name);
}
