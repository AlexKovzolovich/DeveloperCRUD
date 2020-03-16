package ua.epam.resthomework.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

  private static boolean isAppTerminated = false;

  public static boolean isIsAppTerminated() {
    return isAppTerminated;
  }

  @ExceptionHandler
  public ResponseEntity handleException(Exception exception) {
    if (exception instanceof DataAccessException) {
      isAppTerminated = true;
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }
}
