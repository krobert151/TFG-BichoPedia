package com.robertorebolledonaharro.bichoapi.encounters.repo;

import com.robertorebolledonaharro.bichoapi.encounters.model.Encounter;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
public interface EncounterRepository extends JpaRepository<Encounter, UUID> {


    boolean existsById(UUID id);



    @Query("""
            select e from Encounter e
            left join e.specie s where s.id =?1
            """)
    List<Encounter> findBySpecieId(UUID id);

    @Query("""
            select e from Encounter e 
            left join e.userData ud where ud.id= ?1
            """)
    Page<Encounter> findAllByUserData(Pageable pageable, UUID userData);



    @Query("""
            select e.id from Encounter e 
            left join e.specie sp where sp.id= ?1
            """)
    Iterable<UUID> findAllEncounterBySpecie(UUID specieid);

}
