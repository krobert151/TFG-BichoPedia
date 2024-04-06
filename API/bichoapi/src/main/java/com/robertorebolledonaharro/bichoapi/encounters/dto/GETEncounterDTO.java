package com.robertorebolledonaharro.bichoapi.encounters.dto;

import lombok.Builder;

import java.util.UUID;
@Builder
public record GETEncounterDTO(
        UUID id,
        String url,
        String scientificName,
        String type
) {

}