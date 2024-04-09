package com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GETUserSimpleDTO(

        String id,

        String username,

        String email,

        List<String> roles,

        int level,

        String profilePhoto

) {
}
