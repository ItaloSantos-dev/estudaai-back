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
}
