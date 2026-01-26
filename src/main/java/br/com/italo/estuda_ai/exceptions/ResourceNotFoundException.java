package br.com.italo.estuda_ai.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Entidade não encontrada");
    }

    public ResourceNotFoundException(String entity) {
        super(entity + " não encontrado");
    }
}
