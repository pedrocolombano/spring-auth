package br.com.colombano.springauth.exception;

import br.com.colombano.springauth.dto.response.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public StandardError handleNotFoundException(final NotFoundException e) {
        return new StandardError(e.getMessage(), HttpStatus.NOT_FOUND.value(), ZonedDateTime.now());
    }

}
