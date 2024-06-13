package com.robertorebolledonaharro.bichoapi.specie.controller;

import com.robertorebolledonaharro.bichoapi.specie.dto.*;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.SpecieNotFoundException;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user/species")
public class SpecieController {

    private final SpecieService specieService;

    @Operation(summary = "Get species at risk of extinction", description = "Retrieve a list of species at risk of extinction with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of species at risk of extinction returned successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SpecieSimpleDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": "80d768ef-831a-4cfe-9426-fda1eb456464",
                                                    "url": "https://i.pinimg.com/564x/79/94/81/799481a982a56e439756668a19bf35aa.jpg",
                                                    "scientificName": "Gineta"
                                                },
                                                {
                                                    "id": "80d768ef-831a-4cfe-94e6-fda1eb444464",
                                                    "url": "https://i.pinimg.com/564x/c6/31/8d/c6318d184cf1153eb4cf71db7937a214.jpg",
                                                    "scientificName": "American Eagle"
                                                }
                                            ]                                       
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
                                                "message": "No Species was found on page 0",
                                                "path": "/species/danger-extinction/simple",
                                                "dateTime": "22/02/2024 21:23:40"
                                            }                                    
                                                """

                        )}
                ))

    })
    @GetMapping("/danger-extinction/simple")
    public ResponseEntity<List<SpecieSimpleDTO>> getDangerSpecies(
            @Parameter(description = "Number of items per page") @RequestParam(value = "c", required = false, defaultValue = "10") int count,
            @Parameter(description = "Page number") @RequestParam(value = "p", required = false, defaultValue = "0") int page) {
        return ResponseEntity.ok().body(specieService.findSpeciesfromHome(page,count));
    }





    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of species", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SpecieDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "id": "80d768ef-831a-4cfe-94e6-fda1eb445564",
                                                     "url": "https://i.pinimg.com/564x/f8/19/94/f81994a18d2f74abf23c2ae79b1bceb2.jpg",
                                                     "scientificName": "Pleurodelest walts",
                                                     "type": "Salamander"
                                                 },
                                                 {
                                                     "id": "80d768ef-831a-4cfe-94e6-fda1eb444464",
                                                     "url": "https://i.pinimg.com/564x/c6/31/8d/c6318d184cf1153eb4cf71db7937a214.jpg",
                                                     "scientificName": "American Eagle",
                                                     "type": "Bird"
                                                 },
                                                 {
                                                     "id": "80d768ef-831a-4cfe-9426-fda1eb490464",
                                                     "url": "https://i.pinimg.com/564x/04/10/44/04104419e02e72560d0f0c6cff8a8be2.jpg",
                                                     "scientificName": "Araña Lobo",
                                                     "type": "Arachnid"
                                                 }
                                             ]                                       
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
                                        "message": "No Species was found with type:Salamander OR type:Bird or type:Arachnid\\n\\nr",
                                        "path": "/species/allspecies",
                                        "dateTime": "22/02/2024 21:24:44"
                                    }
                                    """

                    )}
            ))

    })
    @GetMapping("/allspecies")
    public ResponseEntity<List<SpecieDTO>>findAllByCriteria(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "c", required = false, defaultValue = "10") int count,
            @RequestParam(value = "p", required = false, defaultValue = "0") int page
    ){
        if(search == null){
            return ResponseEntity.ok(specieService.findAll(page,count));
        }else{
            return ResponseEntity.ok(specieService.findAllByAdvPredicate(search, page, count));

        }
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specie Details", content = {
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
    @GetMapping("/speciebyid/{id}")
    public ResponseEntity<SpecieDetailsDTO> findSpecieById(@PathVariable String id){

        return ResponseEntity.ok(specieService.getDetailsById(UUID.fromString(id)));

    }



    @Operation(summary = "Get all specie names")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of specie names",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SpeciesNameDTO.class)),
                            examples = @ExampleObject(
                                    value = """
                [
                    {"id": "1", "name": "Specie 1"},
                    {"id": "2", "name": "Specie 2"},
                    {"id": "3", "name": "Specie 3"}
                ]"""
                            )
                    )
            )
    })
    @GetMapping("/names")
    public ResponseEntity<List<SpeciesNameDTO>> findAllNames(){
        return ResponseEntity.ok(specieService.findAllNames());
    }







}
