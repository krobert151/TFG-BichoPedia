package com.robertorebolledonaharro.bichoapi.specie.repo;

import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieSimpleDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpeciesNameDTO;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SpecieRepository extends JpaRepository<Specie, UUID>, JpaSpecificationExecutor<Specie> {



    @Query("""
            SELECT new com.robertorebolledonaharro.bichoapi.specie.dto.SpecieSimpleDTO(
            e.id,
            e.media,
            e.scientificName           
            ) FROM Specie e
            WHERE e.danger >2
            """)
    Page<SpecieSimpleDTO> findSpeciesInDangerOfExtintion(Pageable pageable);
    boolean existsById(UUID id);



    @Query("""
            SELECT new com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO(
                e.id,
                e.media,
                e.scientificName,
                e.type
                )FROM Specie e
            """)
    Page<SpecieDTO> findSpeciesDtoPageable(Pageable pageable);

    Optional<Specie> findByScientificName(String name);


}
