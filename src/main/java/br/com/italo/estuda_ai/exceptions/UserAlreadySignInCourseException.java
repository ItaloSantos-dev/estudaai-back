package br.com.italo.estuda_ai.exceptions;

public class UserAlreadySignInCourseException extends RuntimeException {
    public UserAlreadySignInCourseException(String message) {
        super(message);
    }
    public UserAlreadySignInCourseException(String courseName, String userEmail) {
        super(String.format("O email %s já esta cadastrado no curso %s", userEmail, courseName));
    }
}
