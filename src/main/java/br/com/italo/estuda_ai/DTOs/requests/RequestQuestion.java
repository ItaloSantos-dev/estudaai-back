package br.com.italo.estuda_ai.DTOs.requests;

import java.util.UUID;

public record RequestQuestion(
        UUID id,
        String title,
        String link,
        String submodule_id
) {
}
