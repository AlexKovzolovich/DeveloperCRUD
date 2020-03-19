package ua.epam.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j
public class ExceptionControllerAdvice {

  @ExceptionHandler
  public ResponseEntity handleException(Exception exception) {
    log.error(exception.getMessage(), exception);
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }
}
