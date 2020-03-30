package ua.epam.exceptions;

public class AlreadyExistsException extends PersistException {

  public AlreadyExistsException() {
    super("Already exists");
  }
}
