package com.robertorebolledonaharro.bichoapi.specie.controller;

import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpeciePutDTO;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieDangerIncorrectException;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/writer/species")
public class SpecieControllerWriter {

    private final SpecieService specieService;

    @PutMapping("/")
    public ResponseEntity<SpecieDTO> editSpecie(@RequestBody SpeciePutDTO speciePutDTO) throws SpecieDangerIncorrectException {
        return  ResponseEntity.status(HttpStatus.CREATED).body(specieService.updateDetails(speciePutDTO));
    }



}
