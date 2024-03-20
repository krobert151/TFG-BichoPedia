package com.robertorebolledonaharro.bichoapi.specie.dto;

import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.UUID;

@Builder
public record SpecieDTO(
        UUID id,
        String url,
        String scientificName,

        String danger,

        String type
) {

    public static SpecieDTO of(Specie specie){
        return SpecieDTO.builder()
                .id(specie.getId())
                .url(specie.getMedia())
                .scientificName(specie.getScientificName())
                .danger(specie.getDanger().toString())
                .type(specie.getType())
                .build();
    }
}
