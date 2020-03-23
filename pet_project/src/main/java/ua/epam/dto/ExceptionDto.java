package ua.epam.dto;

public class ExceptionDto {

  private String cause;
  private Class<? extends Exception> exceptionClass;

  public ExceptionDto(Exception exception) {
    this.cause = exception.getMessage();
    this.exceptionClass = exception.getClass();
  }

  public String getCause() {
    return cause;
  }

  public Class<? extends Exception> getExceptionClass() {
    return exceptionClass;
  }
}
