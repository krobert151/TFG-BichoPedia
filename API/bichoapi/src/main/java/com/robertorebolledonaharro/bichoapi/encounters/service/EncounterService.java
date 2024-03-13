package com.robertorebolledonaharro.bichoapi.encounters.service;

import com.robertorebolledonaharro.bichoapi.encounters.dto.*;
import com.robertorebolledonaharro.bichoapi.encounters.error.EncounterNotFoundException;
import com.robertorebolledonaharro.bichoapi.encounters.model.Encounter;
import com.robertorebolledonaharro.bichoapi.encounters.repo.EncounterRepository;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import com.robertorebolledonaharro.bichoapi.userdata.model.UserData;
import com.robertorebolledonaharro.bichoapi.userdata.service.UserDataService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class EncounterService {

    private final EncounterRepository repository;
    private final  UserService userService;
    private final SpecieService specieService;
    private final UserDataService userDataService;

    public List<EncounterSimpleDTO> findMostLikedEncounters(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<EncounterSimpleDTO> encounterPage = repository.findEncounterMostLiked(pageable);

        if (encounterPage.hasContent()) {
            return encounterPage.getContent();
        } else {
            throw new EncounterNotFoundException("No encounters found on page " + page);
        }
    }


    @Transactional
    public List<EncounterDTO> findEncounters(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<Encounter> encounterPage = repository.findAll(pageable);

        if (encounterPage.hasContent()) {
            return encounterPage.getContent().stream().map(
                    encounter -> {


                            return EncounterDTO.builder()
                                    .id(encounter.getId())
                                    .scientificName(encounter.getSpecie().getScientificName())
                                    .type(encounter.getSpecie().getType())
                                    .url(
                                            encounter.getMedias().get(0)!=null?encounter.getMedias().get(0):"Manolo")
                                    .build();


                    }
            ).toList();
        } else {
            throw new EncounterNotFoundException("No encounters found on page " + page);
        }
    }

    public List<Marker> findAllEncountersMarkers(){

        return  repository.findAll().stream().map(x ->{

            return Marker.builder()
                    .id(x.getId().toString())
                    .latitud(x.getLocation().split(",")[0])
                    .longuitud(x.getLocation().split(",")[1])
                    .build();

        }).toList();


    }

    @Transactional
    public List<EncounterDTO> findEncountersByUserId(int page, int count, String userid) {

        UserData userData = userDataService.getUserDatafromUserId(userid);


        Pageable pageable = PageRequest.of(page, count);
        Page<Encounter> encounterPage = repository.findAllByUserData(pageable, userData.getId());

        if (encounterPage.hasContent()) {
            return encounterPage.getContent().stream().map(
                    encounter -> {


                            return EncounterDTO.builder()
                                    .id(encounter.getId())
                                    .scientificName(encounter.getSpecie().getScientificName())
                                    .type(encounter.getSpecie().getType())
                                    .url(
                                            encounter.getMedias().get(0)!=null?encounter.getMedias().get(0):"Manolo")
                                    .build();


                    }
            ).toList();
        } else {
            throw new EncounterNotFoundException("No encounters found on page " + page);
        }
    }

    public EncounterPOST addEncounter(EncounterPOST post, String userId){

        Specie specie = specieService.getSpecieById(UUID.fromString(post.specieId()));
        UserData userData = userDataService.getUserDatafromUserId(userId);
        Encounter encounter = Encounter.builder()
                .date(LocalDate.now())
                .likes(0)
                .location(post.location())
                .description(post.description())
                .specie(specie)
                .medias(IntStream.range(0, post.photos().size())
                        .mapToObj(i -> {
                            String x = post.photos().get(i);
                            return "Manolo";

                        }).toList())
                .userData(userData)
                .build();

        repository.save(encounter);

        return post;

    }


    @Transactional
    public EncounterDetailDTO finEncounterDetailById(UUID id){

        Optional<Encounter> optionalEncounter = repository.findById(id);

        if(optionalEncounter.isEmpty()){
            throw new EncounterNotFoundException();
        }

        Encounter encounter = optionalEncounter.get();

        Optional<User> optionalUser = userService.findById(UUID.fromString(encounter.getUserData().getUserId()));

        User user = optionalUser.get();

        return EncounterDetailDTO.builder()
                .scientificName(encounter.getSpecie().getScientificName())
                .mainPhoto(encounter.getMedias().get(0))
                .username(user.getUsername())
                .description(encounter.getDescription())
                .danger(encounter.getSpecie().getDanger().toString())
                .lat(encounter.getLocation().split(",")[0])
                .lon(encounter.getLocation().split(",")[1])
                .media(encounter.getMedias())
                .build();

    }


}
