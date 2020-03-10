package ua.epam.exceptions;

public class FileProcessingException extends Exception{
    public FileProcessingException(String massage) {
        super("Can`t process file " + massage);
    }
}
