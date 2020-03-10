package ua.epam.exceptions;

public class NotExistException extends PersistException {
    public NotExistException() {
        super("Required record don`t exist");
    }
}
