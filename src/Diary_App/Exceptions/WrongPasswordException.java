package Diary_App.Exceptions;

public class WrongPasswordException extends IllegalArgumentException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
