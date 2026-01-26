package br.com.italo.estuda_ai.exceptions;

import org.springframework.http.HttpStatus;

public record ResponseException(
        HttpStatus status,
        String menssage
) {
}
