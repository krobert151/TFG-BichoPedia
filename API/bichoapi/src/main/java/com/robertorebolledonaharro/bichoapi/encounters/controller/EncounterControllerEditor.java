package com.robertorebolledonaharro.bichoapi.encounters.controller;

import com.robertorebolledonaharro.bichoapi.encounters.dto.EncounterDetailDTO;
import com.robertorebolledonaharro.bichoapi.encounters.dto.EncounterPutDTO;
import com.robertorebolledonaharro.bichoapi.encounters.service.EncounterService;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/writer/encounters")
public class EncounterControllerEditor {


    private EncounterService encounterService;

    @PutMapping("/")
    public ResponseEntity<EncounterDetailDTO> editEncounter(@RequestBody EncounterPutDTO encounterPutDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(encounterService.editAnyEncounter(encounterPutDTO));

    }

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
