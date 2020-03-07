package ua.epam.exceptions;

public class PersistException extends RuntimeException{
    public PersistException(String message) {
        super(message);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }
}
