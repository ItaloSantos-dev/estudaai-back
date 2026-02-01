package br.com.italo.estuda_ai.exceptions;

public class FailedLoginException extends RuntimeException {
    public FailedLoginException() {
        super("Credenciais incorretas");
    }
}
