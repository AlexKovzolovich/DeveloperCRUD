package ua.epam.exceptions;

public class WrongArgumentPersistentException extends PersistException {

    public WrongArgumentPersistentException(String message) {
        super("Wrong argument: " + message);
    }
}
