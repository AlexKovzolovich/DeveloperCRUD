package ua.epam.resthomework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler
    public ResponseEntity handleException(Exception exception) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
