package ua.epam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionDto {

  private String cause;

  private Class<? extends Exception> exceptionClass;

  public ExceptionDto(Exception exception) {
    this.cause = exception.getMessage();
    this.exceptionClass = exception.getClass();
  }

}
