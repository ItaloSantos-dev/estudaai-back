package br.com.italo.estuda_ai.DTOs.responses;

import java.time.Duration;
import java.util.UUID;

public record ResponseSubmodule(
        UUID id,
        String name,
        Duration averageDuratio,
        String module,
        String course
) {
}
