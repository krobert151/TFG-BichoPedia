package com.robertorebolledonaharro.bichoapi.userdata.service;

import com.robertorebolledonaharro.bichoapi.article.dto.ArticleSimpleDTO;
import com.robertorebolledonaharro.bichoapi.level.dto.LevelDTO;
import com.robertorebolledonaharro.bichoapi.level.service.LevelService;
import com.robertorebolledonaharro.bichoapi.savedlist.dto.SavedListSimpleDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieArticlesDTO;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieNotFoundException;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import com.robertorebolledonaharro.bichoapi.specie.specification.SpecieSpecification;
import com.robertorebolledonaharro.bichoapi.userdata.dto.UserSimpleDTO;
import com.robertorebolledonaharro.bichoapi.userdata.error.UserNotFoundException;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import com.robertorebolledonaharro.bichoapi.userdata.dto.UserDataDTO;
import com.robertorebolledonaharro.bichoapi.userdata.model.UserData;
import com.robertorebolledonaharro.bichoapi.userdata.repository.UserDataRepository;
import com.robertorebolledonaharro.bichoapi.userdata.specification.UserSpecification;
import com.robertorebolledonaharro.bichoapi.util.CriteriaParser;
import com.robertorebolledonaharro.bichoapi.util.GenericSpecificationsBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User findUserByUserdataId(String id){
        UserData userData = getUserDatafromUserId(id);

        Optional<User> optionalUser = userService.findById(UUID.fromString(id));
        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException();
        }
        return optionalUser.get();

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


    public  UserData getUserDatafromUserId(String id){
        Optional<UserData> optionalUserData = repository.findFirstByUserId(id);
        if(optionalUserData.isEmpty()){
            throw new EntityNotFoundException();
        }
        return optionalUserData.get();
    }

    public List<UserSimpleDTO> findAll(int page, int count){
        Pageable pageable = PageRequest.of(page, count);
        List<UserData>list = repository.findAll();


        if (list.isEmpty()) {
            throw new UserNotFoundException("No users found on page " + page);
        }


        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        List<UserSimpleDTO> content = list.subList(start,end).stream().map(this::userToUserSimpleDTO).toList();

        return new PageImpl<>(content, pageable, list.size()).toList();

    }

    public List<UserSimpleDTO> findAllUsersByAdvPredicate(String search) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<UserData> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<UserData> spec = specBuilder.build(parser.parse(search), UserSpecification::new);
        List<UserData> list = repository.findAll((Sort) spec);
        if(!list.isEmpty()) {
            return list.stream().map(this::userToUserSimpleDTO).toList();
        }else {
            throw new UserNotFoundException("No Users was found with "+search);
        }
    }

    public  UserSimpleDTO userToUserSimpleDTO(UserData userData){
        User user = findUserByUserdataId(userData.getUserId());

        return UserSimpleDTO.builder()
                .id(userData.getId().toString())
                .roles(user.getRoles().stream().map(Enum::name).toList())
                .email(user.getEmail())
                .level(levelService.getLevelFromExp(userData.getExp()).level())
                .username(user.getUsername())
                .profilePhoto(userData.getProfilePhoto())
                .build();

    }

}
