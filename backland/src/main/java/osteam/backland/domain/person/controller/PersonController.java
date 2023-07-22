package osteam.backland.domain.person.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import osteam.backland.domain.person.controller.request.PersonRequest;

@RestController("/person")
public class PersonController {

    @PostMapping
    public String person(PersonRequest personRequest){

    }
}
