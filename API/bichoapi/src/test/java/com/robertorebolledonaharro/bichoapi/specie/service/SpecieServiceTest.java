
package com.robertorebolledonaharro.bichoapi.specie.service;

import com.robertorebolledonaharro.bichoapi.article.model.Article;
import com.robertorebolledonaharro.bichoapi.article.model.TypeOfArticle;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDetailsDTO;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpeciePutDTO;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieDangerIncorrectException;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieNotFoundException;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieScientificNameAlreadyExists;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
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

        assertEquals(expected, result);
    }

    @Test
    void findAllSpeciesDTONotFound(){

        List<Specie> speciesList = List.of();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Specie> speciesPage = new PageImpl<>(speciesList, pageable, 0);
        Mockito.when(repository.findAll(pageable)).thenReturn(speciesPage);

        Assertions.assertThrows(SpecieNotFoundException.class,()-> specieService.findAll(0,10));


    }


    @Test
    void getDetailsByIdNOTFound() {
        UUID specieId = UUID.randomUUID();

        when(repository.findById(specieId)).thenReturn(Optional.empty());
        Assertions.assertThrows(SpecieNotFoundException.class, () -> specieService.getDetailsById(specieId));


    }

    @Test
    void getDetailsById() {
        UUID specieId = UUID.randomUUID();

        Specie specie = Specie.builder()
                .id(specieId)
                .type("Type1")
                .danger(Danger.CR)
                .scientificName("Species 1")
                .articles(List.of(
                        Article.builder()
                                .approved(true)
                                .text("text1")
                                .medias(List.of("media1","media2"))
                                .typeOfArticle(TypeOfArticle.IDENTIFICATION)
                                .title("Identification")
                                .build(),
                        Article.builder()
                                .approved(true)
                                .text("text2")
                                .medias(List.of("media1","media2"))
                                .typeOfArticle(TypeOfArticle.CARES)
                                .title("Cares")
                                .build(),
                        Article.builder()
                                .approved(true)
                                .text("text3")
                                .medias(List.of("media1","media2"))
                                .typeOfArticle(TypeOfArticle.INFO)
                                .title("Info")
                                .build(),
                        Article.builder()
                                .approved(false)
                                .text("text3")
                                .medias(List.of("media1","media2"))
                                .typeOfArticle(TypeOfArticle.INFO)
                                .title("Unaproved")
                                .build()

                ))
                .build();

        when(repository.findById(specieId)).thenReturn(Optional.of(specie));

        SpecieDetailsDTO details = specieService.getDetailsById(specieId);
        assertEquals("Species 1", details.scientificName());
        assertEquals("CR", details.danger());
        assertEquals("sebusca.jpg", details.mainPhoto());

        assertEquals(1, details.identification().size());
        assertEquals(1, details.cares().size());
        assertEquals(1, details.info().size());

        Assertions.assertTrue(details.info().stream().anyMatch(article -> article.title().equals("Info")));
        Assertions.assertTrue(details.identification().stream().anyMatch(article -> article.title().equals("Identification")));
        Assertions.assertTrue(details.cares().stream().anyMatch(article -> article.title().equals("Cares")));
    }

    @Test
    void updateDetails_201() throws SpecieDangerIncorrectException {

        Specie specie1 = Specie.builder()
                .id(UUID.fromString("80d768ef-831a-4cfe-94e6-fda1eb445564"))
                .scientificName("Gallipato")
                .media("gallipato.png")
                .danger(Danger.LC)
                .type("Salamander")
                .build();

        SpeciePutDTO speciePutDTO = SpeciePutDTO.builder()
                .id("80d768ef-831a-4cfe-94e6-fda1eb445564")
                .scientificName("Gallipato")
                .mainPhoto("gallipato.png")
                .danger("LC")
                .type("Salamander")
                .build();

        when(repository.findById(any())).thenReturn(Optional.of(specie1));
        when(repository.existsByScientificName(any())).thenReturn(true);

        when(repository.save(any())).thenReturn(specie1);

        SpecieDTO result = specieService.updateDetails(speciePutDTO);

        assertNotNull(result);
        assertEquals(speciePutDTO.id(), result.id().toString());
        assertEquals(speciePutDTO.scientificName(), result.scientificName());
        assertEquals(speciePutDTO.mainPhoto(), result.url());
        assertEquals(speciePutDTO.danger(), result.danger());
        assertEquals(speciePutDTO.type(), result.type());
        assertNotNull(result);
        assertFalse(result.id().toString().isEmpty());
        assertEquals(specie1.getId().toString(), result.id().toString());


    }

    @Test
    void updateDetails_400ScientificName() throws SpecieScientificNameAlreadyExists {

        Specie specie1 = Specie.builder()
                .id(UUID.fromString("80d768ef-831a-4cfe-94e6-fda1eb445564"))
                .scientificName("Manolo")
                .media("gallipato.png")
                .danger(Danger.LC)
                .type("Salamander")
                .build();

        SpeciePutDTO speciePutDTO = SpeciePutDTO.builder()
                .id("80d768ef-831a-4cfe-94e6-fda1eb445564")
                .scientificName("Gallipato")
                .mainPhoto("gallipato.png")
                .danger("LC")
                .type("Salamander")
                .build();

        when(repository.findById(any())).thenReturn(Optional.of(specie1));
        when(repository.existsByScientificName(any())).thenReturn(true);

        Assertions.assertThrows(SpecieScientificNameAlreadyExists.class, ()->specieService.updateDetails(speciePutDTO));



    }

    @Test
    void updateDetails_400DangerType()  {

        Specie specie1 = Specie.builder()
                .id(UUID.fromString("80d768ef-831a-4cfe-94e6-fda1eb445564"))
                .scientificName("Manolo")
                .media("gallipato.png")
                .danger(Danger.LC)
                .type("Salamander")
                .build();

        SpeciePutDTO speciePutDTO = SpeciePutDTO.builder()
                .id("80d768ef-831a-4cfe-94e6-fda1eb445564")
                .scientificName("Gallipato")
                .mainPhoto("gallipato.png")
                .danger("CC")
                .type("Salamander")
                .build();

        when(repository.findById(any())).thenReturn(Optional.of(specie1));

        Assertions.assertThrows(SpecieDangerIncorrectException.class, ()->specieService.updateDetails(speciePutDTO));



    }


    @Test
    void updateDetails_404()  {

        SpeciePutDTO speciePutDTO = SpeciePutDTO.builder()
                .id("80d768ef-831a-4cfe-94e6-fda1eb435564")
                .scientificName("Gallipato")
                .mainPhoto("gallipato.png")
                .danger("LC")
                .type("Salamander")
                .build();

        when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(SpecieNotFoundException.class, ()->specieService.updateDetails(speciePutDTO));



    }


}