package com.robertorebolledonaharro.bichoapi.encounters.controller;


import com.robertorebolledonaharro.bichoapi.encounters.dto.*;
import com.robertorebolledonaharro.bichoapi.encounters.service.EncounterService;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/encounters")
public class EncounterController {

    private final EncounterService encounterService;

    @Operation(summary = "Get most liked species", description = "Retrieve a list of most liked species with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of most liked species returned successfully", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EncounterSimpleDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": "80d768ef-871a-4cfe-9426-fda1eb490464",
                                                    "scientificName": "Pleurodelest walts",
                                                    "description": "descripcion",
                                                    "photo": "https://i.pinimg.com/564x/f8/19/94/f81994a18d2f74abf23c2ae79b1bceb2.jpg"
                                                },
                                                {
                                                    "id": "80d768ef-871a-4cfe-9426-fda1eb490455",
                                                    "scientificName": "American Eagle",
                                                    "description": "descripcion",
                                                    "photo": "https://i.pinimg.com/564x/04/10/44/04104419e02e72560d0f0c6cff8a8be2.jpg"
                                                },
                                                {
                                                    "id": "80d768ef-871a-4cfe-9426-fda1eb490495",
                                                    "scientificName": "Gineta",
                                                    "description": "descripcion",
                                                    "photo": "https://i.pinimg.com/564x/79/94/81/799481a982a56e439756668a19bf35aa.jpg"
                                                }
                                            ]                                          
                                            """

                            )}
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/most-liked/simple")
    public ResponseEntity<List<EncounterSimpleDTO>> getMostLikedSpecies(
            @Parameter(description = "Number of items per page") @RequestParam(value = "c", required = false, defaultValue = "10") int count,
            @Parameter(description = "Page number") @RequestParam(value = "p", required = false, defaultValue = "0") int page) {
        return ResponseEntity.ok().body(encounterService.findMostLikedEncounters(page, count));
    }

    @GetMapping("/allencounters")
    public ResponseEntity<List<EncounterDTO>> findAllByCriteria(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "c", required = false, defaultValue = "10") int count,
            @RequestParam(value = "p", required = false, defaultValue = "0") int page
    ){
        if(search == null){
            return ResponseEntity.ok(encounterService.findEncounters(page, count));
        }else{
            return ResponseEntity.ok(encounterService.findEncounters(page, count));
        }
    }

    @GetMapping("/myencounters/{id}")
    public ResponseEntity<List<EncounterDTO>> findMyEncounters(
            @RequestParam(value = "c", required = false, defaultValue = "10") int count,
            @RequestParam(value = "p", required = false, defaultValue = "0") int page,
            @PathVariable String id
    ){

            return ResponseEntity.ok(encounterService.findEncountersByUserId(page, count, id));
    }


    @GetMapping("/allmarkers")
    public ResponseEntity<List<Marker>> findAllMarkers(){

        return ResponseEntity.ok(encounterService.findAllEncountersMarkers());

    }

    @GetMapping("/encounterdetails/{id}")
    public ResponseEntity<EncounterDetailDTO> findEncountersDetailsById(@PathVariable String id){
        return ResponseEntity.ok(encounterService.finEncounterDetailById(UUID.fromString(id)));
    }

    @PostMapping("/find/")
    public ResponseEntity<EncounterPOST> saveEncounter(@RequestBody EncounterPOST encounterPOST, @AuthenticationPrincipal User user){
        return ResponseEntity.status(201).body(encounterService.addEncounter(encounterPOST, user.getId().toString()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEncounter(@AuthenticationPrincipal User user, @PathVariable String id){
        boolean deleted = encounterService.deleteMyEncounter(user.getId(), UUID.fromString(id));
        if (deleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
