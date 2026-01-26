package br.com.italo.estuda_ai.DTOs.responses;

import java.time.Duration;
import java.util.UUID;

public record ResponseModule (
        UUID id,
        String name,
        Duration averageDuration,
        String course

){

}
