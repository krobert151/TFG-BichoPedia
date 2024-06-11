package com.robertorebolledonaharro.bichoapi.encounters.dto;

import lombok.Builder;

@Builder
public record GETMarker(

    String id,

    String latitud,

    String longuitud

){}
