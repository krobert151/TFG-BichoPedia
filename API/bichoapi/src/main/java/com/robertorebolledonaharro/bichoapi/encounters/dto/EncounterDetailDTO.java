package com.robertorebolledonaharro.bichoapi.encounters.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record EncounterDetailDTO(

        String scientificName,

        String mainPhoto,

        String danger,

        String username,

        String description,

        List<String> media,

        String lat,

        String lon


) {
}
