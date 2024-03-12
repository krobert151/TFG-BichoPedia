package com.robertorebolledonaharro.bichoapi.encounters.dto;

import java.util.UUID;

public record EncounterSimpleDTO(
        UUID id,

        String scientificName,

        String description,

        String photo


) {
}
