package com.robertorebolledonaharro.bichoapi.user.repository;

import com.robertorebolledonaharro.bichoapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsernameIgnoreCase(String username);

    User findFristById(UUID uuid);

    Optional<User> findFirstByUsername(String username);
}
