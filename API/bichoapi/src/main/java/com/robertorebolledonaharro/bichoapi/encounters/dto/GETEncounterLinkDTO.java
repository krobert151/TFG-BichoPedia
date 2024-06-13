package com.robertorebolledonaharro.bichoapi.encounters.dto;

import com.robertorebolledonaharro.bichoapi.encounters.model.Encounter;
import lombok.Builder;

@Builder
public record GETEncounterLinkDTO(

        String id,

        String name

) {

    public static GETEncounterLinkDTO of(Encounter e){

        return GETEncounterLinkDTO.builder()
                .id(e.getId().toString())
                .name(e.getSpecie().getScientificName()+"_"+e.getDate().toString())
                .build();

    }

}
