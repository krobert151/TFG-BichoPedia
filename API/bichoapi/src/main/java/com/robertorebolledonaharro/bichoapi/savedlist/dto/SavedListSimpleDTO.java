package com.robertorebolledonaharro.bichoapi.savedlist.dto;

import lombok.Builder;

@Builder
public record SavedListSimpleDTO (

        String id,
        String name,

        String photo

){
}
