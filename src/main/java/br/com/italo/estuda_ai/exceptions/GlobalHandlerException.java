package br.com.italo.estuda_ai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException  {
    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ResponseException> handleNotFound(ResourceNotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseException(HttpStatus.NOT_FOUND, exception.getMessage()));
    }
    @ExceptionHandler(ItemAlreadyExistsException.class)
    private ResponseEntity<ResponseException> handleItemAlreaddyExists(ItemAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseException(HttpStatus.CONFLICT, exception.getMessage()));
    }

    @ExceptionHandler(FailedLoginException.class)
    private ResponseEntity<ResponseException> handelFailedLoginException(FailedLoginException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseException(HttpStatus.UNAUTHORIZED, exception.getMessage()));
    }



    @ExceptionHandler(UserAlreadySignInCourseException.class)
    private ResponseEntity<ResponseException> handleUserAlreadySignInCourse(UserAlreadySignInCourseException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseException(HttpStatus.CONFLICT, exception.getMessage()));
    }
}
