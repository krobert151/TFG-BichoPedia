package com.robertorebolledonaharro.bichoapi.user.dto;

public record PUTPermissionsDTO(

        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled

) {
}
