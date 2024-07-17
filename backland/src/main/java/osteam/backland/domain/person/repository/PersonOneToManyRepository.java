package osteam.backland.domain.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.person.entity.PersonOneToMany;

import java.util.UUID;

@Repository
public interface PersonOneToManyRepository extends JpaRepository<PersonOneToMany, UUID> {
}
