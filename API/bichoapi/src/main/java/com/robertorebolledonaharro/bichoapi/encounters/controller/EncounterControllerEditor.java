package com.robertorebolledonaharro.bichoapi.encounters.controller;

import com.robertorebolledonaharro.bichoapi.encounters.dto.GETEncounterDTO;
import com.robertorebolledonaharro.bichoapi.encounters.dto.GETEncounterDetailDTO;
import com.robertorebolledonaharro.bichoapi.encounters.dto.PUTEncounterDTO;
import com.robertorebolledonaharro.bichoapi.encounters.service.EncounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/writer/encounters")
public class EncounterControllerEditor {


    private EncounterService encounterService;


    @Operation(summary = "Find all encounters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of encounters",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GETEncounterDetailDTO.class)))
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allencounters")
    public ResponseEntity<List<GETEncounterDetailDTO>> findAllByCriteria(
            @RequestParam(value = "c", required = false, defaultValue = "10") int count,
            @RequestParam(value = "p", required = false, defaultValue = "0") int page
    ){
        return ResponseEntity.ok(encounterService.findEncountersDetailed(page, count));
    }

    @Operation(summary = "Edit encounter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Encounter edited successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETEncounterDetailDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid encounter data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/")
    public ResponseEntity<GETEncounterDetailDTO> editEncounter(@RequestBody PUTEncounterDTO encounterPutDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(encounterService.editAnyEncounter(encounterPutDTO));
    }

    @Operation(summary = "Delete encounter by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Encounter deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Encounter not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEncounter(@PathVariable String id){
        boolean deleted = encounterService.deleteEncounter(UUID.fromString(id));
        if (deleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



}
