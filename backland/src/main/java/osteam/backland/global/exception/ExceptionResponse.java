package osteam.backland.global.exception;

import lombok.Getter;

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
        this.message = ex.getDetail();
    }
}