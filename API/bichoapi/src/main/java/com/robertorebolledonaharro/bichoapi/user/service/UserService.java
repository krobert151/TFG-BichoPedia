package com.robertorebolledonaharro.bichoapi.user.service;

import com.robertorebolledonaharro.bichoapi.user.dto.CreateUserRequest;
import com.robertorebolledonaharro.bichoapi.user.dto.RestorePassword;
import com.robertorebolledonaharro.bichoapi.user.model.PersonRole;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public Optional<User> findById(UUID userId) {
        return repository.findById(userId);

    }


    public User createUser(CreateUserRequest createUserRequest, EnumSet<PersonRole> roles){
        if (repository.existsByUsernameIgnoreCase(createUserRequest.username()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email del usuario ya ha sido registrado");

        User user = User.builder()
                .username(createUserRequest.username())
                .email(createUserRequest.email())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .roles(roles)
                .build();
        return repository.save(user);

    }

    public User createPersonWithUserRole(CreateUserRequest createUserRequest){
        return createUser(createUserRequest, EnumSet.of(PersonRole.USER));
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

    public void disableExpiratedPassword(User user) {
        user.setCredentialsNonExpired(false);
        repository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return repository.findFirstByUsername(username);
    }
}
