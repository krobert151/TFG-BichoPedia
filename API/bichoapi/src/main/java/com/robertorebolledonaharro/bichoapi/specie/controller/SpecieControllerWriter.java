package com.robertorebolledonaharro.bichoapi.specie.controller;

import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpeciePostDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpeciePutDTO;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.SpecieDangerIncorrectException;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/writer/species")
public class SpecieControllerWriter {

    private final SpecieService specieService;

    @Operation(summary = "Edit specie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Specie edited successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpecieDTO.class),
                            examples = @ExampleObject(
                                    value = """
                {
                    "id": "00000000-0000-0000-0000-000000000000",
                    "url": "http://example.com/specie/1",
                    "scientificName": "Scientific Name",
                    "danger": "High",
                    "type": "Type"
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid specie data",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                    "error": "Bad Request",
                    "message": "The provided specie data is invalid."
                }"""
                            )
                    )
            )
    })
    @PutMapping("/")
    public ResponseEntity<SpecieDTO> editSpecie(@RequestBody SpeciePutDTO speciePutDTO) throws SpecieDangerIncorrectException {
        return  ResponseEntity.status(HttpStatus.CREATED).body(specieService.updateDetails(speciePutDTO));
    }

    @Operation(summary = "Register a new specie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Specie registered successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpecieDTO.class),
                            examples = @ExampleObject(
                                    value = """
                {
                    "id": "00000000-0000-0000-0000-000000000000",
                    "url": "http://example.com/specie/1",
                    "scientificName": "Scientific Name",
                    "danger": "High",
                    "type": "Type"
                }"""
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid specie data",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                {
                    "error": "Bad Request",
                    "message": "The provided specie data is invalid."
                }"""
                            )
                    )
            )
    })
    @PostMapping("/")
    public ResponseEntity<SpecieDTO> registerSpecie(@RequestBody SpeciePostDTO speciePostDTO) throws SpecieDangerIncorrectException {
        return ResponseEntity.status(HttpStatus.CREATED).body(specieService.registerSpecie(speciePostDTO));
    }

    @Operation(summary = "Delete specie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Specie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Specie not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpecie(@PathVariable String id) {
        boolean deleted = specieService.deleteSpecie(UUID.fromString(id));
        if (deleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



}
