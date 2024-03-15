package com.robertorebolledonaharro.bichoapi.encounters.dto;

import lombok.Builder;

import java.util.UUID;
@Builder
public record EncounterSimpleDTO(
        UUID id,

        String scientificName,

        String description,

        String photo


) {
}
