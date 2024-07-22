package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PersonUpdateService {
    private final PersonOneToManyRepository personOneToManyRepository;
    // OneToOne, Only 다 적용해야함.

    public void updatePersonNameByPhone(String name, String phone) {
        personOneToManyRepository.updatePersonNameByPhone(name, phone);
    }
}
