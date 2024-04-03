package com.robertorebolledonaharro.bichoapi.savedlist.service;

import com.robertorebolledonaharro.bichoapi.savedlist.model.SavedList;
import com.robertorebolledonaharro.bichoapi.savedlist.repo.SavedListRepository;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import com.robertorebolledonaharro.bichoapi.specie.repo.SpecieRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SavedListService {

    private final SavedListRepository savedListRepository;
    private final SpecieRepository specieRepository;



        @Transactional
        public void removeSpecieFromSavedLists(UUID specieId) {
            Specie specie = specieRepository.findById(specieId).orElseThrow(() -> new EntityNotFoundException("Specie not found with id: " + specieId));

            List<SavedList> savedLists = savedListRepository.findBySpecie(specieId);

            for (SavedList savedList : savedLists) {
                savedList.getSpecies().remove(specie);
            }

            savedListRepository.saveAll(savedLists);
        }

}
