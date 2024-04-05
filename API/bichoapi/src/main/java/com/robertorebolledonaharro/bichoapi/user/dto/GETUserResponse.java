package com.robertorebolledonaharro.bichoapi.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GETUserResponse {

    protected String id;
    protected String username;
    protected Set<String> roles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime createdAt;


    public static GETUserResponse fromUser(User user) {

        return GETUserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .roles(user.getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .build();
    }

}