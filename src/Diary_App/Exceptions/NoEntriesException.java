package Diary_App.Exceptions;

public class NoEntriesException extends RuntimeException {
    public NoEntriesException(String message) {
        super(message);
    }
}
