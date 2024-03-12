package com.robertorebolledonaharro.bichoapi.userdata.repository;

import com.robertorebolledonaharro.bichoapi.userdata.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, UUID> {

    Optional<UserData> findFirstByUserId(String id);

}
