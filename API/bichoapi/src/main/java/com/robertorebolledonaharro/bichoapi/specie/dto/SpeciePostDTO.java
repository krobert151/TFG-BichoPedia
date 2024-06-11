package com.robertorebolledonaharro.bichoapi.specie.dto;

import lombok.Builder;

@Builder
public record SpeciePostDTO(

    String scientificName,
    String danger,
    String mainPhoto,

    String type
) {
}
