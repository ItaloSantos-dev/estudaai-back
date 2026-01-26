package br.com.italo.estuda_ai.DTOs.requests;

public record RequestModule(
        String name,
        int averageDuration,
        String course_id
) {
}
