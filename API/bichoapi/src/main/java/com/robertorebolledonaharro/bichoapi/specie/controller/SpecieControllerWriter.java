package com.robertorebolledonaharro.bichoapi.specie.controller;

import com.robertorebolledonaharro.bichoapi.common.error.exeptions.SpecieNotFoundException;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDetailsDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpeciePostDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpeciePutDTO;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.SpecieDangerIncorrectException;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specie Details",
                    content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SpecieDetailsDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "ScientificName": "Pleurodelest walts",
                                                "danger": "VU",
                                                "mainPhoto": "gallipato.png",
                                                "info": [
                                                    {
                                                        "title": "Titulo wapo",
                                                        "description": "texto wapo",
                                                        "archives": [
                                                            "profilephoto.png"
                                                        ]
                                                    }
                                                ],
                                                "identification": [
                                                    {
                                                        "title": "Titulo wapo2",
                                                        "description": "texto wapo2",
                                                        "archives": [
                                                            "profilephoto.png"
                                                        ]
                                                    }
                                                ],
                                                "cares": [
                                                    {
                                                        "title": "Titulo wapo3",
                                                        "description": "texto wapo2",
                                                        "archives": [
                                                            "profilephoto.png"
                                                        ]
                                                    }
                                                ]
                                            }
                                            """

                            )}
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Species not found", content =
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SpecieNotFoundException.class)),
                    examples = {@ExampleObject(
                            value = """
                                        {
                                            "status": "NOT_FOUND",
                                            "message": "No Species was found",
                                            "path": "/species/speciebyid/80d768ef-831a-4cfe-94e6-fda1eb445561",
                                            "dateTime": "20/03/2024 18:44:49"
                                        }
                                    """

                    )}
            ))

    })
    @PutMapping(value = "/",produces = "application/json", consumes = "application/json")
    public ResponseEntity<SpecieDTO> editSpecie(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Specie DTO ot edit the Specie",
            content = @Content(
                    schema=@Schema(implementation = SpeciePutDTO.class)
            )
        ) SpeciePutDTO speciePutDTO
    ) throws SpecieDangerIncorrectException {
        return  ResponseEntity.status(HttpStatus.CREATED).body(specieService.updateDetails(speciePutDTO));
    }

    @PostMapping("/")
    public ResponseEntity<SpecieDTO> registerSpecie(@RequestBody SpeciePostDTO speciePostDTO) throws SpecieDangerIncorrectException {

        return ResponseEntity.status(HttpStatus.CREATED).body(specieService.registerSpecie(speciePostDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SpecieDTO> deleteSpecie(@PathVariable String id) {
        boolean deleted = specieService.deleteSpecie(UUID.fromString(id));
        if (deleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
