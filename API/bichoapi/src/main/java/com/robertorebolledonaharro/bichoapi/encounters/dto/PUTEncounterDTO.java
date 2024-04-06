package com.robertorebolledonaharro.bichoapi.encounters.dto;

import java.util.List;

public record PUTEncounterDTO(
        String encounterId,

        String specieId,

        String description,

        String location,

        List<String> photos


) {
}
