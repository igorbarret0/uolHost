package com.uolHost.uolHost_backend_challenge.exceptionHandler;

import com.uolHost.uolHost_backend_challenge.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValid(MethodArgumentNotValidException exception) {

        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                "Invalid data at create user",
                "422"
        );

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AppealExhaustedException.class)
    public ResponseEntity<Object> appealExhausted(AppealExhaustedException exception) {

        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                "404"
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReadXMLException.class)
    public ResponseEntity<Object> readXml(ReadXMLException exception) {

        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                "500"
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ReadJSONException.class)
    public ResponseEntity<Object> readJson(ReadJSONException exception) {

        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                "500"
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
