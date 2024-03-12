package com.robertorebolledonaharro.bichoapi.security.blacklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<TokenBlackList, String> {


}
