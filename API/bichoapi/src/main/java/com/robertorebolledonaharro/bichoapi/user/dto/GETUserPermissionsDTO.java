package com.robertorebolledonaharro.bichoapi.user.dto;

import com.robertorebolledonaharro.bichoapi.user.model.User;
import lombok.Builder;

@Builder
public record GETUserPermissionsDTO(

        String username,

        String email,

        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled

) {

    public static GETUserPermissionsDTO of(User user){

        return GETUserPermissionsDTO.builder()
                .username(user.getUsername())
                .enabled(user.isEnabled())
                .email(user.getEmail())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .build();


    }

}
