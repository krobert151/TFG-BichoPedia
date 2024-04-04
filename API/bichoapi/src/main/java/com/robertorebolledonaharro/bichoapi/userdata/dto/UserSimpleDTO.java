package com.robertorebolledonaharro.bichoapi.userdata.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserSimpleDTO(

        String id,

        String username,

        String email,

        List<String> roles,

        int level,

        String profilePhoto

) {
}
