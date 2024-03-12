package com.robertorebolledonaharro.bichoapi.userdata.dto;

import lombok.Builder;

@Builder
public record UserDataDTO (

        String username,

        String email,

        String userPhoto,

        int encounters,

        int level,

        int articles,

        int percentExp


) {
}
