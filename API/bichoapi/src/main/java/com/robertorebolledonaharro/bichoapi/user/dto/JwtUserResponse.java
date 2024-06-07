package com.robertorebolledonaharro.bichoapi.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtUserResponse extends GETUserResponse {

    private String token;
    private String refreshToken;

    public JwtUserResponse(GETUserResponse userResponse) {
        id = userResponse.getId();
        username = userResponse.getUsername();
        roles=userResponse.getRoles();
        createdAt = userResponse.getCreatedAt();
    }

    public static JwtUserResponse of (User user, String token) {
        JwtUserResponse result = new JwtUserResponse(fromUser(user));
        result.setToken(token);
        return result;

    }

}