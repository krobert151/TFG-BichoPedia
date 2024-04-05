package com.robertorebolledonaharro.bichoapi.user.controller;

import com.robertorebolledonaharro.bichoapi.savedlist.dto.SavedListSimpleDTO;
import com.robertorebolledonaharro.bichoapi.user.dto.UserDataDTO;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/userData")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDataDTO> findUserDataByUserID(@PathVariable String id){

        return ResponseEntity.ok(service.findUserDataByUserId(id));

    }

    @GetMapping("/savedlist/{id}")
    public ResponseEntity<List<SavedListSimpleDTO>> findSavedListByUserId(@PathVariable String id){
        return ResponseEntity.ok(service.getSavedListSimpleDTOfromuserId(id));
    }

}
