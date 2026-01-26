package br.com.italo.estuda_ai.DTOs.requests;


public record RequestCourse(
        String name,
        String description,
        int averageDuration
) {
}
