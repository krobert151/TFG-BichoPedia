package com.robertorebolledonaharro.bichoapi.savedlist.repo;

import com.robertorebolledonaharro.bichoapi.savedlist.model.SavedList;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedListRepository extends JpaRepository<SavedList, UUID> {
    @Query("SELECT sl FROM SavedList sl JOIN sl.species s WHERE s.id = :id")
    List<SavedList> findBySpecie(UUID id);

}
