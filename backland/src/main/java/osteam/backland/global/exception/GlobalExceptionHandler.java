package osteam.backland.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice // 전역 예외 처리
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler { // 추상 클래스 상속
    /*
     * 비즈니스 로직과 관련된 커스텀 Exception handler
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex) {
        log.error("CuntomException:: ", ex); // 이거 없으면 안뜸.
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}