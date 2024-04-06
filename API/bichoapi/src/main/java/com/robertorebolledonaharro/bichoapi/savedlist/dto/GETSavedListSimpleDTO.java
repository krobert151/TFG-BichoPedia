package com.robertorebolledonaharro.bichoapi.savedlist.dto;

import lombok.Builder;

@Builder
public record GETSavedListSimpleDTO(

        String id,
        String name,

        String photo

){
}
