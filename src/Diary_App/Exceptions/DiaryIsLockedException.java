package Diary_App.Exceptions;

public class DiaryIsLockedException extends RuntimeException {
    public DiaryIsLockedException(String message) {
        super(message);
    }
}
