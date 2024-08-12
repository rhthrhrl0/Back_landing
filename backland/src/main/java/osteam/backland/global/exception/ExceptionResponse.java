package osteam.backland.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.model.CustomException;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int statusCode;
    private final String error;
    private final String message;

    public ExceptionResponse(CustomException ex) {
        this.statusCode = ex.getStatus().value();
        this.error = ex.getStatus().name();
        this.message = ex.getErrorMessage();
    }

    public ExceptionResponse(HttpStatus errorCode, String message) {
        this.statusCode = errorCode.value();
        this.error = errorCode.name();
        this.message = message;
    }
}