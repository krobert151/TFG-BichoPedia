package com.robertorebolledonaharro.bichoapi.user.controller;

import com.robertorebolledonaharro.bichoapi.user.dto.GETUserDetailsDTO;
import com.robertorebolledonaharro.bichoapi.user.dto.POSTUserDTO;
import com.robertorebolledonaharro.bichoapi.user.dto.GETUserSimpleDTO;
import com.robertorebolledonaharro.bichoapi.user.error.PersonRoleIncorrectException;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/user")
public class UserAdminController {

    private final UserService userService;


    @GetMapping("/allusers")
    public ResponseEntity<List<GETUserSimpleDTO>> findAllByCriteria(
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

    @PostMapping("/")
    public ResponseEntity<POSTUserDTO> createUserAdvancedDTOResponse(@RequestBody POSTUserDTO createUserAdvancedDTO) throws PersonRoleIncorrectException {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(createUserAdvancedDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GETUserDetailsDTO> getUserDetailsDTOResponseEntity(@PathVariable String id){
        return ResponseEntity.ok(userService.findUserDetails(id));

    }


}
