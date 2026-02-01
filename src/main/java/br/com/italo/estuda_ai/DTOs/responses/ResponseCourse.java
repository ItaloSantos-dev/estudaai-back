package br.com.italo.estuda_ai.DTOs.responses;

import java.time.Duration;

public record ResponseCourse(
        String name,
        String description,
        Duration averageDuration
) {
}
