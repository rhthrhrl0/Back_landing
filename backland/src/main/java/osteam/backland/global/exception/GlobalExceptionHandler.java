package osteam.backland.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
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


    // MethodArgumentNotValid 처리
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;

            String fieldName = fieldError.getField();       // 이름
            String message = fieldError.getDefaultMessage();  // 에러 메세지
            Object value = fieldError.getRejectedValue(); // 주입된 실제 값. 널일 수 있어서 toString() 호출하기 애매함.

            log.error("fieldName : " + fieldName);
            log.error("message : " + message);
            log.error("value : " + value);
        });
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}