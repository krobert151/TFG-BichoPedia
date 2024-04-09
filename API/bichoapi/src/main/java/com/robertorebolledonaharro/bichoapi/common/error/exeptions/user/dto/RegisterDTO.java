package com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.dto;

import com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.validator.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ValidPassword
public record RegisterDTO(

        @NotNull(message = "username can`t be null")
        @NotBlank(message = "username can`t be black")
        String username,

        @NotNull(message = "email can`t be null")
        String email,

        String password



) {



}
