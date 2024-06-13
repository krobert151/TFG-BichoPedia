package com.robertorebolledonaharro.bichoapi.encounters.dto;

import java.time.LocalDate;
import java.util.List;

public record PUTEncounterDTO(
        String encounterId,

        String specieId,

        String description,

        String location,

        LocalDate date,

        List<String> photos


) {
}
