package br.com.italo.estuda_ai.DTOs.requests;

public record RequestSubmodule(
        String name,
        String description,
        int averageDuration,
        String module_id
)
{
}
