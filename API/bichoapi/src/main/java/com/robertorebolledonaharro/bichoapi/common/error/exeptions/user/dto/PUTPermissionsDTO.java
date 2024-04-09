package com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.dto;

public record PUTPermissionsDTO(

        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled

) {
}
