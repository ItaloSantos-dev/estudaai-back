package br.com.italo.estuda_ai.DTOs.responses;

import java.util.UUID;

public record ResponseLink(
        UUID id,
        String title,
        String link,
        String submodule,
        String module,
        String course
) {
}
