package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.dto.PersonOneToManyDTO;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PersonSearchService {
    private final PersonOneToManyRepository personOneToManyRepository;

    public Optional<PersonOneToManyDTO> searchPersonOneToManyByName(String name) {
        return personOneToManyRepository.searchByName(name) // Spring Data JPA 사용
                .map(PersonOneToManyDTO::fromOneToMany);
    }

    public Optional<PersonOneToManyDTO> searchPersonOneToManyByPhone(String phone) {
        return personOneToManyRepository.searchByPhone(phone) // Spring Data JPA 사용
                .map(PersonOneToManyDTO::fromOneToMany);
    }

    public List<PersonOneToManyDTO> searchAllPersonOneToMany() {
        return personOneToManyRepository.findAll().stream()
                .map(PersonOneToManyDTO::fromOneToMany)
                .toList();
    }
}
