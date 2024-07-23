package osteam.backland.domain.person.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.entity.dto.PersonOneToManyDTO;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;
import osteam.backland.domain.person.repository.PersonOneToOneRepository;
import osteam.backland.domain.person.repository.PersonOnlyRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class PersonValidator {
    private final PersonOnlyRepository personOnlyRepository;
    private final PersonOneToOneRepository personOneToOneRepository;
    private final PersonOneToManyRepository personOneToManyRepository;

    public Optional<PersonOneToManyDTO> validateAlreadyExistOneToMany(String phone) {
        return personOneToManyRepository.searchByPhone(phone)
                .map(PersonOneToManyDTO::fromOneToMany);
    }

    public Optional<PersonDTO> validateAlreadyExistOnly(String phone) {
        return personOnlyRepository.searchByPhone(phone)
                .map(PersonDTO::from);
    }

    public Optional<PersonDTO> validateAlreadyExistOneToOne(String phone) {
        return personOneToOneRepository.searchByPhone(phone)
                .map(PersonDTO::from);
    }
}
