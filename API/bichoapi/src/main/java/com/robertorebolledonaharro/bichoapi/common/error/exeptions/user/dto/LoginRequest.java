package com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest{

    @NotNull(message = "username can`t be null")
    @NotBlank(message = "username can`t be black")
    private String username;


    @NotNull(message = "password can`t be null")
    @NotBlank(message = "password can`t be black")
    private String password;

}
