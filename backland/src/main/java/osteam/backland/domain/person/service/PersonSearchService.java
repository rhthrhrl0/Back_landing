package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.domain.person.entity.dto.PersonOneToManyDTO;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;
import osteam.backland.global.exception.CustomException;
import osteam.backland.global.exception.ErrorCode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PersonSearchService {
    private final PersonOneToManyRepository personOneToManyRepository;

    public List<PersonOneToManyDTO> searchPersonOneToManyByName(String name) {
        return personOneToManyRepository.searchByName(name)
                .map(this::convertToOneToManyDtos)
                .orElse(Collections.emptyList());
    }

    public PersonOneToManyDTO searchPersonOneToManyByPhone(String phone) {
        return personOneToManyRepository.searchByPhone(phone)
                .map(PersonOneToManyDTO::fromOneToMany)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "phone번호가 " + phone + "인 사람을 찾을 수 없습니다."));
    }

    public List<PersonOneToManyDTO> searchAllPersonOneToMany() {
        return personOneToManyRepository.searchAll()
                .map(this::convertToOneToManyDtos)
                .orElse(Collections.emptyList());
    }

    private List<PersonOneToManyDTO> convertToOneToManyDtos(List<PersonOneToMany> entities) {
        return entities.stream()
                .map(PersonOneToManyDTO::fromOneToMany)
                .collect(Collectors.toList());
    }
}
