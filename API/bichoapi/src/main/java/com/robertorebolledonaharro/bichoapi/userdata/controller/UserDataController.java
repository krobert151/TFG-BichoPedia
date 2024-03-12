package com.robertorebolledonaharro.bichoapi.userdata.controller;

import com.robertorebolledonaharro.bichoapi.savedlist.dto.SavedListSimpleDTO;
import com.robertorebolledonaharro.bichoapi.userdata.dto.UserDataDTO;
import com.robertorebolledonaharro.bichoapi.userdata.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userData")
public class UserDataController {

    private final UserDataService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDataDTO> findUserDataByUserID(@PathVariable String id){

        return ResponseEntity.ok(service.findUserDatabyUserId(id));

    }

    @GetMapping("/savedlist/{id}")
    public ResponseEntity<List<SavedListSimpleDTO>> findSavedListByUserId(@PathVariable String id){
        return ResponseEntity.ok(service.getSavedListSimpleDTOfromuserId(id));
    }

}
