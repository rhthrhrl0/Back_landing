package osteam.backland.domain.person.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.domain.person.entity.dto.PersonDTO;

@Service
@Slf4j
public class PersonCreateService {

    public PersonDTO createAll(String name, String phone) {

        return null;
    }

    /**
     * Phone과 OneToOne 관계인 person 생성
     */
    public PersonDTO oneToOne(String name, String phone) {
        return null;
    }

    /**
     * Phone과 OneToMany 관계인 person 생성
     */
    public PersonDTO oneToMany(String name, String phone) {
        return null;
    }

    /**
     * person 하나로만 구성되어 있는 생성
     */
    public PersonDTO one(String name, String phone) {
        return null;
    }
}
