package osteam.backland.global.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final String errorMessage;

    /*
     * 비즈니스 exception 처리 시 추가적인 내용이 필요할 경우
     */
    public CustomException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);

        this.status = httpStatus;
        this.errorMessage = errorMessage;
    }

    /*
     * field exception 처리 시 사용
     */
    public CustomException(HttpStatus httpStatus, String errorMessage, Throwable e) {
        super(errorMessage, e);

        this.status = httpStatus;
        this.errorMessage = errorMessage;
    }
}
