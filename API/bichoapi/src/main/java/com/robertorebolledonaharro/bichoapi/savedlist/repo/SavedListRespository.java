package com.robertorebolledonaharro.bichoapi.savedlist.repo;

import com.robertorebolledonaharro.bichoapi.savedlist.model.SavedList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedListRespository extends JpaRepository<SavedList, UUID> {
}
