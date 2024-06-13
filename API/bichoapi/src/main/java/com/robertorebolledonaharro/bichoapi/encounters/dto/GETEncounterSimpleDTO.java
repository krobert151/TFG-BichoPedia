package com.robertorebolledonaharro.bichoapi.encounters.dto;

import lombok.Builder;

import java.util.UUID;
@Builder
public record GETEncounterSimpleDTO(
        UUID id,

        String scientificName,

        String description,

        String photo


) {
}
