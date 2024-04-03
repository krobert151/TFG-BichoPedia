package com.robertorebolledonaharro.bichoapi.specie.dto;

import lombok.Builder;

@Builder
public record SpeciePutDTO(

        String id,

        String scientificName,
        String danger,
        String mainPhoto,

        String type

) {
}
