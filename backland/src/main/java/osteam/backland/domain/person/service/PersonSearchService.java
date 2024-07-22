package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.PersonOnly;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.entity.dto.PersonOneToManyDTO;
import osteam.backland.domain.person.entity.dto.search.SearchAllDTO;
import osteam.backland.domain.person.entity.dto.search.SearchByNameDTO;
import osteam.backland.domain.person.entity.dto.search.SearchByPhoneDTO;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;
import osteam.backland.domain.person.repository.PersonOneToOneRepository;
import osteam.backland.domain.person.repository.PersonOnlyRepository;
import osteam.backland.global.exception.CustomException;
import osteam.backland.global.exception.ErrorCode;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PersonSearchService {
    private final PersonOnlyRepository personOnlyRepository;
    private final PersonOneToOneRepository personOneToOneRepository;
    private final PersonOneToManyRepository personOneToManyRepository;

    // 이름 기반 검색
    public SearchByNameDTO searchAllByName(String name) {
        return SearchByNameDTO.builder()
                .personOnly(searchPersonOnlyByName(name))
                .personOneToOne(searchPersonOneToOneByName(name))
                .personOneToMany(searchPersonOneToManyByName(name))
                .build();
    }

    private List<PersonDTO> searchPersonOnlyByName(String name) {
        return personOnlyRepository.searchByName(name)
                .map(this::convertToOnlyDTOs)
                .orElse(Collections.emptyList());
    }

    private List<PersonDTO> searchPersonOneToOneByName(String name) {
        return personOneToOneRepository.searchByName(name)
                .map(this::convertToOneToOneDTOs)
                .orElse(Collections.emptyList());
    }

    private List<PersonOneToManyDTO> searchPersonOneToManyByName(String name) {
        return personOneToManyRepository.searchByName(name)
                .map(this::convertToOneToManyDTOs)
                .orElse(Collections.emptyList());
    }


    // 휴대폰 번호 기반 검색
    public SearchByPhoneDTO searchAllByPhone(String phone) {
        return SearchByPhoneDTO.builder()
                .personOnly(searchPersonOnlyByPhone(phone))
                .personOneToOne(searchPersonOneToOneByPhone(phone))
                .personOneToMany(searchPersonOneToManyByPhone(phone))
                .build();
    }

    private PersonDTO searchPersonOnlyByPhone(String phone) {
        return personOnlyRepository.searchByPhone(phone)
                .map(PersonDTO::from)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "phone번호가 " + phone + "인 사람을 찾을 수 없습니다."));
    }

    private PersonDTO searchPersonOneToOneByPhone(String phone) {
        return personOneToOneRepository.searchByPhone(phone)
                .map(PersonDTO::from)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "phone번호가 " + phone + "인 사람을 찾을 수 없습니다."));
    }

    private PersonOneToManyDTO searchPersonOneToManyByPhone(String phone) {
        return personOneToManyRepository.searchByPhone(phone)
                .map(PersonOneToManyDTO::fromOneToMany)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "phone번호가 " + phone + "인 사람을 찾을 수 없습니다."));
    }

    // 전체 검색
    public SearchAllDTO searchAll() {
        return SearchAllDTO.builder()
                .personOnly(searchAllPersonOnly())
                .personOneToOne(searchAllPersonOneToOne())
                .personOneToMany(searchAllPersonOneToMany())
                .build();
    }

    private List<PersonDTO> searchAllPersonOnly() {
        return personOnlyRepository.searchAll()
                .map(this::convertToOnlyDTOs)
                .orElse(Collections.emptyList());
    }

    private List<PersonDTO> searchAllPersonOneToOne() {
        return personOneToOneRepository.searchAll()
                .map(this::convertToOneToOneDTOs)
                .orElse(Collections.emptyList());
    }

    private List<PersonOneToManyDTO> searchAllPersonOneToMany() {
        return personOneToManyRepository.searchAll()
                .map(this::convertToOneToManyDTOs)
                .orElse(Collections.emptyList());
    }

    // 변환 메소드
    private List<PersonDTO> convertToOnlyDTOs(List<PersonOnly> entities) {
        return entities.stream()
                .map(PersonDTO::from)
                .collect(Collectors.toList());
    }

    private List<PersonDTO> convertToOneToOneDTOs(List<PersonOneToOne> entities) {
        return entities.stream()
                .map(PersonDTO::from)
                .collect(Collectors.toList());
    }

    private List<PersonOneToManyDTO> convertToOneToManyDTOs(List<PersonOneToMany> entities) {
        return entities.stream()
                .map(PersonOneToManyDTO::fromOneToMany)
                .collect(Collectors.toList());
    }
}
