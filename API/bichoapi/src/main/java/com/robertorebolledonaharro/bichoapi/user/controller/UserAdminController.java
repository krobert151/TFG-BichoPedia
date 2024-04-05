package com.robertorebolledonaharro.bichoapi.user.controller;

import com.robertorebolledonaharro.bichoapi.user.dto.CreateUserAdvancedDTO;
import com.robertorebolledonaharro.bichoapi.user.dto.UserSimpleDTO;
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

    @PostMapping("/")
    public ResponseEntity<CreateUserAdvancedDTO> createUserAdvancedDTOResponse(@RequestBody CreateUserAdvancedDTO createUserAdvancedDTO) throws PersonRoleIncorrectException {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(createUserAdvancedDTO));
    }


}
