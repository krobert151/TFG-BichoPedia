package com.robertorebolledonaharro.bichoapi.user.controller;

import com.robertorebolledonaharro.bichoapi.security.blacklist.BlackListService;
import com.robertorebolledonaharro.bichoapi.security.jwt.access.JwtProvider;
import com.robertorebolledonaharro.bichoapi.user.dto.CreateUserRequest;
import com.robertorebolledonaharro.bichoapi.user.dto.JwtUserResponse;
import com.robertorebolledonaharro.bichoapi.user.dto.LoginRequest;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authManager;
    private final UserService userService;
    private final BlackListService blackListService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register as user", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = JwtUserResponse.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": "80d768ef-831a-4cfe-94e6-fda1eb4452a6",
                                                 "username": "rducker0",
                                                 "roles": [
                                                     "ADMIN"
                                                 ],
                                                 "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI4MGQ3NjhlZi04MzFhLTRjZmUtOTRlNi1mZGExZWI0NDUyYTYiLCJpYXQiOjE3MDI0MTA2MDEsImV4cCI6MTcwMjQ5NzAwMX0.A0nmPr3mI_QiJiN-kld8qFzegDybOVtqYDYh_TwRigarVi1PK9wMraEcwQcSRHRM"
                                             }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400", description = "Introduced data not valid", content = @Content)
    })
    @Operation(summary = "createPersonWithUserRole", description = "Register as user")
    @PostMapping("/auth/register")
    public ResponseEntity<JwtUserResponse> createPersonWithUserRole(@Valid @RequestBody CreateUserRequest createUserRequest){
        userService.register(createUserRequest);
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                createUserRequest.username(),
                                createUserRequest.password()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        System.out.println(user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.of(user, token));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "log in", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = JwtUserResponse.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": "80d768ef-831a-4cfe-94e6-fda1eb4452a6",
                                                 "username": "rducker0",
                                                 "roles": [
                                                     "ADMIN"
                                                 ],
                                                 "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI4MGQ3NjhlZi04MzFhLTRjZmUtOTRlNi1mZGExZWI0NDUyYTYiLCJpYXQiOjE3MDI0MTA2MDEsImV4cCI6MTcwMjQ5NzAwMX0.A0nmPr3mI_QiJiN-kld8qFzegDybOVtqYDYh_TwRigarVi1PK9wMraEcwQcSRHRM"
                                             }
                                            """
                            )}

                    )}),
            @ApiResponse(responseCode = "400", description = "Introduced data not valid", content = @Content)
    })
    @Operation(summary = "login", description = "log in")
    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        System.out.println(user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.of(user, token));
    }

    @PostMapping("/userLogout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        blackListService.addToBlackList(token);


        return ResponseEntity.ok("Logged out successfully");
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }

}
