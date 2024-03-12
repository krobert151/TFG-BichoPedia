package com.robertorebolledonaharro.bichoapi.specie.dto;

import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import lombok.Builder;

import java.util.UUID;

@Builder
public record SpecieDTO(
        UUID id,
        String url,
        String scientificName,
        String type
) {
    public static SpecieDTO of(Specie specie){
        return SpecieDTO.builder()
                .id(specie.getId())
                .url(specie.getMedia().getArchive())
                .scientificName(specie.getScientificName())
                .type(specie.getType())
                .build();
    }
}
