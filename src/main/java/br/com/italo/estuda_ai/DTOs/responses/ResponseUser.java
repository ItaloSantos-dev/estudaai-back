package br.com.italo.estuda_ai.DTOs.responses;

import br.com.italo.estuda_ai.model.enums.UserRole;

import java.time.LocalDate;

public record ResponseUser(
        String name,
        String email,
        UserRole role,
        LocalDate nasciment
) {
}
