package com.robertorebolledonaharro.bichoapi.userdata.controller;

import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import com.robertorebolledonaharro.bichoapi.userdata.dto.UserSimpleDTO;
import com.robertorebolledonaharro.bichoapi.userdata.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/user")
public class UserAdminController {

    private final UserDataService userService;


    @GetMapping("/allusers")
    public ResponseEntity<List<UserSimpleDTO>> findAllByCriteria(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "c", required = false, defaultValue = "10") int count,
            @RequestParam(value = "p", required = false, defaultValue = "0") int page
    ){
        if(search == null){
            return ResponseEntity.ok(userService.findAll(page,count));
        }else{
            return ResponseEntity.ok(userService.findAllUsersByAdvPredicate(search));

        }
    }


}
