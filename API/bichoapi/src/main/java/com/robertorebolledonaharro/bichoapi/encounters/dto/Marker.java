package com.robertorebolledonaharro.bichoapi.encounters.dto;

import lombok.Builder;

@Builder
public record Marker (

    String id,

    String latitud,

    String longuitud

){}
