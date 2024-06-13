package com.robertorebolledonaharro.bichoapi.user.controller;

import com.robertorebolledonaharro.bichoapi.common.error.exeptions.IncorrectPasswordException;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.PersonRoleIncorrectException;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.UsernameAlreadyExistsException;
import com.robertorebolledonaharro.bichoapi.user.dto.*;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@AllArgsConstructor
@Tag(name = "Users Enpoints", description = "Endpoints for managing users")
@RequestMapping("/admin/user")
public class UserAdminController {

    private final UserService userService;

    @Operation(summary = "Get all users or search users by criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETUserSimpleDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                                [{
                                                id: 1234567890
                                                "username":"krobert152",
                                                "email": "robertorebolledo152@gail.com",
                                                "password": "Lagarto_Wapo32",
                                                "roles":[
                                                    "ADMIN",
                                                    "USER",
                                                    "MANOLO"
                                                ],
                                                "profilePhoto":"fotowapa"}]
                    """
                            )
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid user data",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Bad Request",
                    "message": "The user data provided is invalid. Please check the request body for errors."
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Internal Server Error",
                    "message": "An unexpected error occurred on the server. Please try again later."
                }"""
                            )
                    )
            )
    })
    @GetMapping("/allusers")
    public ResponseEntity<List<GETUserSimpleDTO>> findAllByCriteria(
            @Parameter(description = "Search criteria for users (if its pageable, this field must be null)", example = "john")
            @RequestParam(value = "search", required = false) String search,

            @Parameter(description = "Number of users to return per page", example = "10")
            @RequestParam(value = "c", required = false, defaultValue = "100") int count,

            @Parameter(description = "Page number to return", example = "0")
            @RequestParam(value = "p", required = false, defaultValue = "0") int page
    ){
        if(search == null){
            return ResponseEntity.ok(userService.findAll(page,count));
        }else{
            return ResponseEntity.ok(userService.findAllUsersByAdvPredicate(search));

        }
    }


    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = POSTUserDTO.class),
                            examples = @ExampleObject(
                                    value = """
                    {
                    "username":"krobert152",
                    "email": "robertorebolledo152@gail.com",
                    "password": "Lagarto_Wapo32",
                    "roles":[
                        "ADMIN",
                        "USER",
                        "MANOLO"
                    ],
                    "profilePhoto":"fotowapa"
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid user data",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Bad Request",
                    "message": "The user data provided is invalid. Please check the request body for errors."
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Internal Server Error",
                    "message": "An unexpected error occurred on the server. Please try again later."
                }"""
                            )
                    )
            )
    })
    @PostMapping("/")
    public ResponseEntity<POSTUserDTO> createUserAdvancedDTOResponse(@RequestBody POSTUserDTO createUserAdvancedDTO) throws PersonRoleIncorrectException, UsernameAlreadyExistsException, IncorrectPasswordException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(createUserAdvancedDTO));
    }

    @Operation(summary = "Get user details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user details",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETUserDetailsDTO.class),
                            examples = @ExampleObject(
                                    value = """
                    {
                    "id": "1234567890",
                    "username": "john_doe",
                    "email": "john@example.com",
                    "profilePhoto": "http://example.com/photos/john.jpg",
                    "roles": ["USER", "ADMIN"],
                    "encounters": [
                        {
                            "id": "encounter123",
                            "name": "SpeciesName_2024-06-11"
                        },
                        {
                            "id": "encounter456",
                            "name": "AnotherSpeciesName_2024-06-12"
                        }
                    ],
                    "articles": [
                        {
                            "id": "article123",
                            "name": "Article 1"
                        },
                        {
                            "id": "article456",
                            "name": "Article 2"
                        }
                    ],
                    "savedLists": [
                        {
                            "id": "list123",
                            "name": "Saved List 1"
                        },
                        {
                            "id": "list456",
                            "name": "Saved List 2"
                        }
                    ],
                    "level": 5,
                    "exp": 100,
                    "percentExp": 50,
                    "accountNonExpired": true,
                    "accountNonLocked": true,
                    "credentialsNonExpired": true,
                    "enabled": true,
                    "createdAt": "2024-06-11",
                    "old": "false",
                    "passwordExpiredAt": "2025-06-11"
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Not Found",
                    "message": "The user with the specified ID was not found."
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Internal Server Error",
                    "message": "An unexpected error occurred on the server. Please try again later."
                }"""
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<GETUserDetailsDTO> getUserDetailsDTOResponseEntity(@Parameter(description = "The ID of the user to retrieve", example = "1234567890") @PathVariable String id) {
        return ResponseEntity.ok(userService.findUserDetails(id));
    }

    @Operation(summary = "Update user basic information by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User information updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETUserSimpleDTO.class),
                            examples = @ExampleObject(
                                    value = """
                    {
                    "id": "1",
                    "username": "john_doe",
                    "email": "john@example.com",
                    "roles": ["USER"],
                    "level": 5,
                    "profilePhoto": "http://example.com/photos/john.jpg"
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid user data",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Bad Request",
                    "message": "The provided user data is invalid."
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Not Found",
                    "message": "The user with the specified ID was not found."
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Internal Server Error",
                    "message": "An unexpected error occurred on the server. Please try again later."
                }"""
                            )
                    )
            )
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<GETUserSimpleDTO> update(@Parameter(description = "The ID of the user to update", example = "1") @PathVariable String id,
                                                   @RequestBody PUTUserBasicInfoDTO putUserBasicInfoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, putUserBasicInfoDTO));
    }

    @Operation(summary = "Update user permissions by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User permissions updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETUserPermissionsDTO.class),
                            examples = @ExampleObject(
                                    value = """
                    {
                    "username": "john_doe",
                    "email": "john@example.com",
                    "accountNonExpired": true,
                    "accountNonLocked": true,
                    "credentialsNonExpired": true,
                    "enabled": true
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid permissions data",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Bad Request",
                    "message": "The provided permissions data is invalid."
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Not Found",
                    "message": "The user with the specified ID was not found."
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Internal Server Error",
                    "message": "An unexpected error occurred on the server. Please try again later."
                }"""
                            )
                    )
            )
    })
    @PutMapping("/update/permissions/{id}")
    public ResponseEntity<GETUserPermissionsDTO> updatePermissions(@PathVariable String id, @RequestBody PUTPermissionsDTO putPermissionsDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.changePermissions(id,putPermissionsDTO));

    }

    @Operation(summary = "Update user roles by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User roles updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETUserSimpleDTO.class),
                            examples = @ExampleObject(
                                    value = """
                    {
                    "id": "1",
                    "username": "john_doe",
                    "email": "john@example.com",
                    "roles": ["USER", "ADMIN"],
                    "level": 5,
                    "profilePhoto": "http://example.com/photos/john.jpg"
                    }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid roles data",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Bad Request",
                    "message": "The provided roles data is invalid."
                    }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Not Found",
                    "message": "The user with the specified ID was not found."
                    }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                    {
                    "error": "Internal Server Error",
                    "message": "An unexpected error occurred on the server. Please try again later."
                    }"""
                            )
                    )
            )
    })
    @PutMapping("/update/roles/{id}")
    public ResponseEntity<GETUserSimpleDTO> updateRoles(@PathVariable String id, @RequestBody PUTRolesDTO rolesDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.changeRoles(id,rolesDTO));

    }

    @Operation(summary = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                    "error": "Not Found",
                    "message": "The user with the specified ID was not found."
                }"""
                            )
                    )
            )
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
