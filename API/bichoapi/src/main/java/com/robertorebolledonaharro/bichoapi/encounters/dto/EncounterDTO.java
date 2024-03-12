package com.robertorebolledonaharro.bichoapi.encounters.dto;

import com.robertorebolledonaharro.bichoapi.encounters.model.Encounter;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import lombok.Builder;

import java.util.UUID;
@Builder
public record EncounterDTO(
        UUID id,
        String url,
        String scientificName,
        String type
) {

}