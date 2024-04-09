package com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.dto;

import lombok.Builder;

@Builder
public record GETUserProfileDTO(

        String username,

        String email,

        String userPhoto,

        int encounters,

        int level,

        int articles,

        int percentExp


) {
}
