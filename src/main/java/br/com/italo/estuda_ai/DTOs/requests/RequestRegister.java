package br.com.italo.estuda_ai.DTOs.requests;

import br.com.italo.estuda_ai.model.enums.UserRole;

import java.time.LocalDate;

public record RequestRegister(
        String name,
        String email,
        String password,
        LocalDate nasciment,
        UserRole role
) {
}
