package osteam.backland.domain.person.exception;

public class PersonNotFoundException extends IllegalArgumentException{

    public PersonNotFoundException(String message) {
        super(message);
    }
}
