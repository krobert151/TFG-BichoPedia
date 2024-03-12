package com.robertorebolledonaharro.bichoapi.specie.dto;

import java.util.UUID;

public record SpecieSimpleDTO(

        UUID id,

        String url,

        String scientificName

) {



}
