package com.robertorebolledonaharro.bichoapi.level.repo;

import com.robertorebolledonaharro.bichoapi.level.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, UUID> {


    @Query("select l from Level l where l.fromExp <= ?1 and l.toExp >= ?1")
    Optional<Level> findLevelByExp(int exp);

}
