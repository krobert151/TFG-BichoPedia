package com.robertorebolledonaharro.bichoapi.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;
@Schema(description = "Simple User Data Transfer Object")
@Builder
public record GETUserSimpleDTO(

        @Schema(description = "User ID", example = "1")
        String id,

        @Schema(description = "Username", example = "john_doe")
        String username,

        @Schema(description = "User email", example = "john@example.com")
        String email,

        @Schema(description = "List of roles assigned to the user", example = "[\"USER\"]")
        List<String> roles,

        @Schema(description = "User level", example = "5")
        int level,

        @Schema(description = "URL to user's profile photo", example = "http://example.com/photos/john.jpg")
        String profilePhoto

) {
}