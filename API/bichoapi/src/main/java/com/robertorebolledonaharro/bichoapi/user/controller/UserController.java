package com.robertorebolledonaharro.bichoapi.user.controller;

import com.robertorebolledonaharro.bichoapi.user.dto.GETUserProfileDTO;
import com.robertorebolledonaharro.bichoapi.user.dto.GETUserSimpleDTO;
import com.robertorebolledonaharro.bichoapi.user.dto.PUTUserBasicInfoDTO;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.savedlist.dto.GETSavedListSimpleDTO;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/userData")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<GETUserProfileDTO> findUserDataByUserID(@PathVariable String id){

        return ResponseEntity.ok(service.findUserDataDTOByUserId(id));

    }

    @GetMapping("/savedlist/{id}")
    public ResponseEntity<List<GETSavedListSimpleDTO>> findSavedListByUserId(@PathVariable String id){
        return ResponseEntity.ok(service.getSavedListSimpleDTOfromuserId(id));
    }

    @PutMapping("/update")
    public ResponseEntity<GETUserSimpleDTO> update(@AuthenticationPrincipal User user, @RequestBody PUTUserBasicInfoDTO putUserBasicInfoDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateUser(user.getId().toString(),putUserBasicInfoDTO));

    }

}
