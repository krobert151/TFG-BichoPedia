package com.robertorebolledonaharro.bichoapi.userdata.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateUserAdvancedDTO(

        @NotNull(message = "username can`t be null")
        @NotBlank(message = "username can`t be black")
        String username,

        @NotNull(message = "email can`t be null")
        String email,

        String password,

        String profilePhoto,

        List<String> roles





) {



}