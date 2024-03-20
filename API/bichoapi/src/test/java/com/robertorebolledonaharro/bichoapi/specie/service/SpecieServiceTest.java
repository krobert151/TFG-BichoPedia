
package com.robertorebolledonaharro.bichoapi.specie.service;

import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieNotFoundException;
import com.robertorebolledonaharro.bichoapi.specie.model.Danger;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import com.robertorebolledonaharro.bichoapi.specie.repo.SpecieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecieServiceTest {

    @InjectMocks
    private SpecieService specieService;

    @Mock
    private SpecieRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void findAllSpecieDTOSucces() {

        List<Specie> speciesList = List.of(
                Specie.builder().id(UUID.randomUUID()).type("Type1").danger(Danger.CR).scientificName("Species 1").build(),
                Specie.builder().id(UUID.randomUUID()).type("Type2").danger(Danger.EN).scientificName("Species 2").build());

        Pageable pageable = PageRequest.of(0, 10);
        Page<Specie> speciesPage = new PageImpl<>(speciesList, pageable, speciesList.size());

        when(repository.findAll(pageable)).thenReturn(speciesPage);


        List<SpecieDTO> result = specieService.findAll(0, 10);

        List<SpecieDTO> expected = speciesList.stream()
                .map(specie -> SpecieDTO.builder()
                        .id(specie.getId()) // Convert UUID to String
                        .url(specie.getMedia() != null && !specie.getMedia().isEmpty() ? specie.getMedia() : "sebusca.jpg")
                        .type(specie.getType())
                        .danger(specie.getDanger() != null && !specie.getDanger().toString().isEmpty() ? specie.getDanger().toString() : "uncertain")
                        .scientificName(specie.getScientificName())
                        .build())
                .collect(Collectors.toList());

        Assertions.assertEquals(expected, result);
    }

    @Test
    void findAllSpeciesDTONotFound(){

        List<Specie> speciesList = List.of();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Specie> speciesPage = new PageImpl<>(speciesList, pageable, 0);
        Mockito.when(repository.findAll(pageable)).thenReturn(speciesPage);

        Assertions.assertThrows(SpecieNotFoundException.class,()-> specieService.findAll(0,10));


    }


}