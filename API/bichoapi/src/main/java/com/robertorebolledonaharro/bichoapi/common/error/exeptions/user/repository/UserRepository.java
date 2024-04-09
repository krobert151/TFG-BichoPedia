package com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.repository;

import com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String username);


    User findFristById(UUID uuid);

    Optional<User> findFirstByUsername(String username);
}
