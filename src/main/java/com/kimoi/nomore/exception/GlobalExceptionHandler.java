package com.kimoi.nomore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kimoi.nomore.dto.exception.ErrorMessageResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundErrorException.class)
    public ResponseEntity<?> notFound(NotFoundErrorException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageResponse(exception.getMessage()));
    }

    @ExceptionHandler(BadRequestErrorException.class)
    public ResponseEntity<?> badRequest(BadRequestErrorException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageResponse(exception.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<?> internalServerError(InternalServerErrorException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessageResponse(exception.getMessage()));
    }

    // 클라이언트가 지정된 시간 내에 요청을 완료하지 않아 서버가 연결을 닫은 경우
    @ExceptionHandler(RequestTimeoutErrorException.class)
    public ResponseEntity<?> requestTimeout(RequestTimeoutErrorException exception) {
        return ResponseEntity
                .status(HttpStatus.REQUEST_TIMEOUT)
                .body(new ErrorMessageResponse(exception.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> forbidden(IllegalStateException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorMessageResponse(exception.getMessage()));
    }

    // 성공 메시지
    @ExceptionHandler(SucessException.class)
    public ResponseEntity<?> success(SucessException exception) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorMessageResponse(exception.getMessage()));
    }

}
