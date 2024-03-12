package com.robertorebolledonaharro.bichoapi.level.service;

import com.robertorebolledonaharro.bichoapi.level.dto.LevelDTO;
import com.robertorebolledonaharro.bichoapi.level.model.Level;
import com.robertorebolledonaharro.bichoapi.level.repo.LevelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository repository;

    public LevelDTO getLevelFromExp(int exp){

        Optional<Level> optionalLevel = repository.findLevelByExp(exp);

        if(optionalLevel.isEmpty()){
            throw new EntityNotFoundException();
        }

        Level level = optionalLevel.get();

        int levelExpRate = level.getToExp() - level.getFromExp();
        int levelExp = exp - level.getFromExp();


        int percent = (levelExp*100)/levelExpRate;

        return LevelDTO.builder()
                .level(level.getLevel())
                .percent(percent)
                .build();


    }

}
