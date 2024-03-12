package com.robertorebolledonaharro.bichoapi.encounters.dto;

import java.util.List;

public record EncounterPOST (

        String specieId,

        String description,

        String location,

        List<String> photos

){
}
