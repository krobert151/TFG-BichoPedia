package com.robertorebolledonaharro.bichoapi.user.service;

import com.robertorebolledonaharro.bichoapi.level.dto.LevelDTO;
import com.robertorebolledonaharro.bichoapi.level.service.LevelService;
import com.robertorebolledonaharro.bichoapi.savedlist.dto.SavedListSimpleDTO;
import com.robertorebolledonaharro.bichoapi.user.dto.*;
import com.robertorebolledonaharro.bichoapi.user.model.PersonRole;
import com.robertorebolledonaharro.bichoapi.user.error.PersonRoleIncorrectException;
import com.robertorebolledonaharro.bichoapi.user.error.UserNotFoundException;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.model.UserData;
import com.robertorebolledonaharro.bichoapi.user.repository.UserDataRepository;
import com.robertorebolledonaharro.bichoapi.user.repository.UserRepository;
import com.robertorebolledonaharro.bichoapi.user.specification.UserSpecification;
import com.robertorebolledonaharro.bichoapi.util.CriteriaParser;
import com.robertorebolledonaharro.bichoapi.util.GenericSpecificationsBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository repository;
    private final UserDataRepository dataRepository;
    private final LevelService levelService;
    private final PasswordEncoder passwordEncoder;


    public User findUserById(UUID userId) {

        Optional<User> user = repository.findById(userId);

        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get();


    }

    public User findUserByUsername(String username) {

        Optional<User> user = repository.findFirstByUsername(username);

        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get();


    }

    public UserData findUserDataFromUserId(String id){
        Optional<UserData> optionalUserData = dataRepository.findFirstByUserId(id);
        if(optionalUserData.isEmpty()){
            throw new EntityNotFoundException();
        }
        return optionalUserData.get();
    }

    @Transactional
    public User findUserByUserdataId(String id){
        UserData userData = findUserDataFromUserId(id);

        Optional<User> optionalUser = repository.findById(UUID.fromString(id));
        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException();
        }
        return optionalUser.get();

    }

    public List<UserSimpleDTO> findAll(int page, int count){
        Pageable pageable = PageRequest.of(page, count);
        List<UserData>list = dataRepository.findAll();


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
        List<UserData> list = dataRepository.findAll((Sort) spec);
        if(!list.isEmpty()) {
            return list.stream().map(this::userToUserSimpleDTO).toList();
        }else {
            throw new UserNotFoundException("No Users was found with "+search);
        }
    }


    public void createUser(CreateUserRequest createUserRequest, EnumSet<PersonRole> roles){
        validNewUser(createUserRequest);

        User user = User.builder()
                .username(createUserRequest.username())
                .email(createUserRequest.email())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .roles(roles)
                .build();
        repository.save(user);

    }

    public void register(CreateUserRequest createUserRequest){
        createUser(createUserRequest, EnumSet.of(PersonRole.USER));
    }

    public User restorePassword(RestorePassword restorePassword) throws UserPrincipalNotFoundException {

        Optional<User> optionalPerson = repository.findFirstByUsername(restorePassword.username());

        if(optionalPerson.isEmpty())
            throw new UserPrincipalNotFoundException("Username with name "+ restorePassword.username()+" not found");

        User user = optionalPerson.get();

        if(!passwordEncoder.matches(restorePassword.oldPassword(), user.getPassword())) {
            throw new EntityNotFoundException("Old password not found ");
        }

        user.setPassword(passwordEncoder.encode(restorePassword.password()));

        user.setEnabled(true);

        user.setCredentialsNonExpired(true);

        user.setLastPasswordChangeAt(LocalDateTime.now());

        user.setPasswordExpirateAt(LocalDateTime.now().plusMonths(3));

        return repository.save(user);

    }

    public boolean checkPasswordExpired(UUID uuid) {
        User user = repository.findFristById(uuid);


        System.out.println(user.getPasswordExpirateAt()+ "Expirated");
        System.out.println(LocalDateTime.now()+"Now");
        if(user.getPasswordExpirateAt().isBefore(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    public void disableExpiatedPassword(User user) {
        user.setCredentialsNonExpired(false);
        repository.save(user);
    }

    @Transactional
    public UserDataDTO findUserDataByUserId(String id){

        UserData userData = findUserDataFromUserId(id);

        Optional<User> optionalUser = repository.findById(UUID.fromString(id));
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

        UserData userData = findUserDataFromUserId(id);

        return userData.getSavedLists().stream().map(x-> {

            return SavedListSimpleDTO.builder()
                   .id(x.getId().toString())
                   .name(x.getTitle())
                   .photo(x.getSpecies().get(0).getMedia())
                   .build();

        }).toList();

    }

    public CreateUserAdvancedDTO createNewUser(CreateUserAdvancedDTO createUserAdvancedDTO) throws PersonRoleIncorrectException  {

        validNewUser(createUserAdvancedDTO);

        EnumSet<PersonRole> roles = EnumSet.noneOf(PersonRole.class);

        for (String s:
                createUserAdvancedDTO.roles()) {
        }


        User user = User.builder()
                .username(createUserAdvancedDTO.username())
                .email(createUserAdvancedDTO.email())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(createUserAdvancedDTO.password()))
                .roles(roles)
                .build();

        user = repository.save(user);

        UserData userData = UserData.builder()
                .savedLists(new ArrayList<>())
                .articles(new ArrayList<>())
                .encounters(new ArrayList<>())
                .profilePhoto(createUserAdvancedDTO.profilePhoto())
                .userId(user.getId().toString())
                .build();
        dataRepository.save(userData);

        return createUserAdvancedDTO;

    }

    public void validNewUser(CreateUserRequest user){
        if (repository.existsByUsernameIgnoreCase(user.username()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email del usuario ya ha sido registrado");

        if (repository.existsByEmailIgnoreCase(user.email()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email del usuario ya ha sido registrado");

        Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");

        if(!textPattern.matcher(user.password()).matches()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email del usuario ya ha sido registrado");
        }
    }

    public void validNewUser(CreateUserAdvancedDTO user){
        if (repository.existsByUsernameIgnoreCase(user.username()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email del usuario ya ha sido registrado");

        if (repository.existsByEmailIgnoreCase(user.email()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email del usuario ya ha sido registrado");

        Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");

        if(!textPattern.matcher(user.password()).matches()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email del usuario ya ha sido registrado");
        }

        user.roles().forEach(this::isRoleValid);


    }

    public void isRoleValid(String role) {

        boolean showError = false;

        for(PersonRole p : PersonRole.values()){
            if (p.name().equals(role)) {
                showError = true;
            }

        }

        if(!showError){
            try {
                throw new PersonRoleIncorrectException(role+" is not a valid Role");
            } catch (PersonRoleIncorrectException e) {
                throw new RuntimeException(e);
            }
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
