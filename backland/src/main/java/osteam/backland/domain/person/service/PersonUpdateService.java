package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;
import osteam.backland.domain.person.repository.PersonOneToOneRepository;
import osteam.backland.domain.person.repository.PersonOnlyRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PersonUpdateService {
    private final PersonOnlyRepository personOnlyRepository;
    private final PersonOneToOneRepository personOneToOneRepository;
    private final PersonOneToManyRepository personOneToManyRepository;

    public void updatePersonOnlyNameByPhone(PersonDTO personDTO) {
        personOnlyRepository.updatePersonNameByPhone(personDTO.getName(), personDTO.getPhone());
    }

    public void updatePersonOneToOneNameByPhone(PersonDTO personDTO) {
        personOneToOneRepository.updatePersonNameByPhone(personDTO.getName(), personDTO.getPhone());
    }

    public void updatePersonOneToManyNameByPhone(PersonDTO personDTO) {
        personOneToManyRepository.updatePersonNameByPhone(personDTO.getName(), personDTO.getPhone());
    }
}
