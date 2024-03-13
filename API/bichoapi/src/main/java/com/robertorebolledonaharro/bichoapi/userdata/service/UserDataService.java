package com.robertorebolledonaharro.bichoapi.userdata.service;

import com.robertorebolledonaharro.bichoapi.level.dto.LevelDTO;
import com.robertorebolledonaharro.bichoapi.level.service.LevelService;
import com.robertorebolledonaharro.bichoapi.savedlist.dto.SavedListSimpleDTO;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import com.robertorebolledonaharro.bichoapi.userdata.dto.UserDataDTO;
import com.robertorebolledonaharro.bichoapi.userdata.model.UserData;
import com.robertorebolledonaharro.bichoapi.userdata.repository.UserDataRepository;
import jakarta.persistence.EntityNotFoundException;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final UserDataRepository repository;
    private final UserService userService;
    private final LevelService levelService;

    @Transactional
    public UserDataDTO findUserDatabyUserId(String id){

        UserData userData = getUserDatafromUserId(id);

        Optional<User> optionalUser = userService.findById(UUID.fromString(id));
        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException();
        }
        User user = optionalUser.get();
        LevelDTO levelDTO = levelService.getLevelFromExp(userData.getExp());


        return UserDataDTO.builder()
                .username(user.getUsername())
                .level(levelDTO.level())
                .userPhoto(userData.getProfilePhoto())
                .percentExp(levelDTO.percent())
                .email(user.getEmail())
                .articles(userData.getArticles().size())
                .encounters(userData.getEncounters().size())
                .build();


    }


    @Transactional
    public List<SavedListSimpleDTO> getSavedListSimpleDTOfromuserId(String id){

        UserData userData = getUserDatafromUserId(id);

        return userData.getSavedLists().stream().map(x-> {

            return SavedListSimpleDTO.builder()
                   .id(x.getId().toString())
                   .name(x.getTitle())
                   .photo(x.getSpecies().get(0).getMedia())
                   .build();

        }).toList();

    }


    public UserData getUserDatafromUserId(String id){
        Optional<UserData> optionalUserData = repository.findFirstByUserId(id);
        if(optionalUserData.isEmpty()){
            throw new EntityNotFoundException();
        }
        return optionalUserData.get();
    }

}
