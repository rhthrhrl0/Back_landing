package osteam.backland.domain.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.repository.custom.onetoone.PersonOneToOneCustomRepository;
import osteam.backland.domain.person.repository.custom.only.PersonOnlyCustomRepository;

import java.util.UUID;

@Repository
public interface PersonOneToOneRepository extends JpaRepository<PersonOneToOne, UUID>, PersonOneToOneCustomRepository {
}
