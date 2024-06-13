package com.robertorebolledonaharro.bichoapi.user.controller;

import com.robertorebolledonaharro.bichoapi.user.dto.GETUserProfileDTO;
import com.robertorebolledonaharro.bichoapi.user.dto.GETUserSimpleDTO;
import com.robertorebolledonaharro.bichoapi.user.dto.PUTUserBasicInfoDTO;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.savedlist.dto.GETSavedListSimpleDTO;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user/userData")
public class UserController {

    private final UserService service;

    @Operation(summary = "Find user data by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user data",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETUserProfileDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GETUserProfileDTO> findUserDataByUserID(@PathVariable String id){
        return ResponseEntity.ok(service.findUserDataDTOByUserId(id));
    }

    @Operation(summary = "Find saved list by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved saved list",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GETSavedListSimpleDTO.class)))
            ),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/savedlist/{id}")
    public ResponseEntity<List<GETSavedListSimpleDTO>> findSavedListByUserId(@PathVariable String id){
        return ResponseEntity.ok(service.getSavedListSimpleDTOfromuserId(id));
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETUserSimpleDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid user data"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update")
    public ResponseEntity<GETUserSimpleDTO> update(@AuthenticationPrincipal User user, @RequestBody PUTUserBasicInfoDTO putUserBasicInfoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateUser(user.getId().toString(),putUserBasicInfoDTO));
    }

}
