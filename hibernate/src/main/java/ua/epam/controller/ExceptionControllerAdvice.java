package ua.epam.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.epam.dto.ExceptionDto;
import ua.epam.exceptions.AlreadyExistsException;
import ua.epam.exceptions.NotExistException;

@ControllerAdvice
@Log4j
public class ExceptionControllerAdvice {

  @ExceptionHandler(AlreadyExistsException.class)
  public ResponseEntity<ExceptionDto> handleAlreadyExistsException(Exception exception) {
    log.error(exception.getMessage());
    return new ResponseEntity<>(new ExceptionDto(exception), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(NotExistException.class)
  public ResponseEntity<ExceptionDto> handleNotExistsException(Exception exception) {
    log.error(exception.getMessage());
    return new ResponseEntity<>(new ExceptionDto(exception), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDto> handleException(Exception exception) {
    log.error(exception.getMessage());
    return new ResponseEntity<>(new ExceptionDto(exception), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
