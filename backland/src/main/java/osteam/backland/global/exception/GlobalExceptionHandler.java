package osteam.backland.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import osteam.backland.domain.person.exception.PersonNotFoundException;

@RestControllerAdvice // 전역 예외 처리
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler { // 추상 클래스 상속

    // NotFoundAccountException 발생시 에러 처리
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<?> handleNotFoundEntity(PersonNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}