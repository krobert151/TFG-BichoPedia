package com.robertorebolledonaharro.bichoapi.encounters.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record GETEncounterDetailDTO(

        String id,

        String scientificName,

        String mainPhoto,

        String danger,

        String type,

        String date,

        String username,

        String description,

        List<String> media,

        String lat,

        String lon


) {
}
