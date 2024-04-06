package com.robertorebolledonaharro.bichoapi.savedlist.dto;

import com.robertorebolledonaharro.bichoapi.savedlist.model.SavedList;
import lombok.Builder;

@Builder
public record GETSavedListLinkDTO(
        String id,

        String name

) {

    public static GETSavedListLinkDTO of (SavedList s){
        return GETSavedListLinkDTO.builder()
                .id(s.getId().toString())
                .name(s.getTitle())
                .build();

    }

}
