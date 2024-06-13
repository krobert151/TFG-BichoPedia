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
import org.springframework.data.repository.query.Param;

public interface SpecieRepository extends JpaRepository<Specie, UUID>, JpaSpecificationExecutor<Specie> {



    @Query("""
            SELECT new com.robertorebolledonaharro.bichoapi.specie.dto.SpecieSimpleDTO(
            e.id,
            e.media,
            e.scientificName           
            ) FROM Specie e
            WHERE e.danger = 'EW'
            """)
    Page<SpecieSimpleDTO> findSpeciesInDangerOfExtintion(Pageable pageable);
    boolean existsById(UUID id);

    @Query("SELECT s FROM Specie s JOIN s.articles a WHERE a.id = :articleId")
    Specie findSpecieFromArticleId(@Param("articleId") UUID articleId);

    boolean existsByScientificName(String name);

    Optional<Specie> findByScientificName(String name);


}
