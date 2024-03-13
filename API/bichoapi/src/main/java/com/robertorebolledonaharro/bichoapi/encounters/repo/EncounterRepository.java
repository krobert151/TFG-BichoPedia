package com.robertorebolledonaharro.bichoapi.encounters.repo;

import com.robertorebolledonaharro.bichoapi.encounters.dto.EncounterSimpleDTO;
import com.robertorebolledonaharro.bichoapi.encounters.model.Encounter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EncounterRepository extends JpaRepository<Encounter, UUID> {

    @Query("""
        SELECT new com.robertorebolledonaharro.bichoapi.encounters.dto.EncounterSimpleDTO(
            e.id,
            es.scientificName,
            e.description,
            e.medias.get(0)
        )
        FROM Encounter e
        LEFT JOIN e.specie es
        ORDER BY e.likes DESC
    """)
    Page<EncounterSimpleDTO> findEncounterMostLiked (Pageable pageable);

    boolean existsById(UUID id);


    @Query("""
            select e from Encounter e 
            left join e.userData ud where ud.id= ?1
            """)
    Page<Encounter> findAllByUserData(Pageable pageable, UUID userData);


}
