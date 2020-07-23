package kr.huple.jeju2ri.configuration.controlleradvice;

import kr.huple.jeju2ri.configuration.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handlerRuntimeException(RuntimeException e, HttpServletRequest req) {
        log.error("================= Handler RuntimeException =================");
        e.printStackTrace();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "RuntimeException : " + e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                HttpServletRequest req) {
        log.error("================= Handler MethodArgumentNotValidException =================");
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "MethodArgumentNotValidException : " + e.getMessage());
    }

}
