package com.robertorebolledonaharro.bichoapi.encounters.service;

import com.robertorebolledonaharro.bichoapi.common.service.CommonService;
import com.robertorebolledonaharro.bichoapi.encounters.dto.*;
import com.robertorebolledonaharro.bichoapi.encounters.error.EncounterNotFoundException;
import com.robertorebolledonaharro.bichoapi.encounters.error.UnauthorizedEncounterAccessException;
import com.robertorebolledonaharro.bichoapi.encounters.model.Encounter;
import com.robertorebolledonaharro.bichoapi.encounters.repo.EncounterRepository;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import com.robertorebolledonaharro.bichoapi.specie.service.SpecieService;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.model.UserData;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class EncounterService  {

    private final EncounterRepository repository;
    private final  UserService userService;
    private final SpecieService specieService;
    private final UserService userDataService;

    private final CommonService service;

    @Autowired
    public EncounterService(
            @Lazy EncounterRepository encounterRepository,
            @Lazy  UserService userService,
            @Lazy  SpecieService specieService,
            @Lazy UserService userDataService,
            @Lazy CommonService service
    ){

        this.repository = encounterRepository;
        this.userService = userService;
        this.specieService = specieService;
        this.userDataService = userDataService;
        this.service = service;

    }



    public List<GETEncounterSimpleDTO> findMostLikedEncounters(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        List<Encounter>list = repository.findAll();



        if (list.isEmpty()) {
            throw new EncounterNotFoundException("No encounters found on page " + page);
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        List<GETEncounterSimpleDTO> content = list.subList(start,end).stream().map(x->{
            return GETEncounterSimpleDTO.builder()
                    .id(x.getId())
                    .scientificName(x.getSpecie().getScientificName())
                    .description(x.getDescription())
                    .photo(x.getMedias().get(0))
                    .build();
        }).toList();

        return new PageImpl<>(content, pageable, list.size()).toList();



    }


    @Transactional
    public List<GETEncounterDTO> findEncounters(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<Encounter> encounterPage = repository.findAll(pageable);

        if (encounterPage.hasContent()) {
            return encounterPage.getContent().stream().map(
                    encounter -> {


                            return GETEncounterDTO.builder()
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

    public List<GETMarker> findAllEncountersMarkers(){

        return  repository.findAll().stream().map(x ->{

            return GETMarker.builder()
                    .id(x.getId().toString())
                    .latitud(x.getLocation().split(",")[0])
                    .longuitud(x.getLocation().split(",")[1])
                    .build();

        }).toList();


    }

    @Transactional
    public List<GETEncounterDTO> findEncountersByUserId(int page, int count, String userid) {

        UserData userData = userDataService.findUserDataFromUserId(userid);


        Pageable pageable = PageRequest.of(page, count);
        Page<Encounter> encounterPage = repository.findAllByUserData(pageable, userData.getId());

        if (encounterPage.hasContent()) {
            return encounterPage.getContent().stream().map(
                    encounter -> {


                            return GETEncounterDTO.builder()
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

    public POSTEncounterDTO addEncounter(POSTEncounterDTO post, String userId){

        Specie specie = specieService.getSpecieById(UUID.fromString(post.specieId()));
        UserData userData = userDataService.findUserDataFromUserId(userId);
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
    public GETEncounterDetailDTO finEncounterDetailById(UUID id){

        Optional<Encounter> optionalEncounter = repository.findById(id);

        if(optionalEncounter.isEmpty()){
            throw new EncounterNotFoundException();
        }

        Encounter encounter = optionalEncounter.get();

        return encounterToEncounterDetailDTO(encounter);


    }

    public void deleteAllEncounterFromSpecie(UUID id){

        repository.deleteAllByIdInBatch(repository.findAllEncounterBySpecie(id));


    }

    @Transactional
    public boolean deleteMyEncounter(UUID userId, UUID encounterId){

        UserData userData = userDataService.findUserDataFromUserId(userId.toString());

        Optional<Encounter> encounter = repository.findById(encounterId);

        if(encounter.isEmpty()){
            throw new EncounterNotFoundException("no encounter with the id:"+encounterId.toString()+" was found");
        }


        if(!userData.getEncounters().contains(encounter.get())){
            throw new UnauthorizedEncounterAccessException("Este encuentro no es tuyo");

        }

        return deleteEncounter(encounterId);


    }

    public boolean deleteEncounter(UUID id){

        if(!repository.existsById(id)){
            throw new EncounterNotFoundException("no encounter with the id:"+id.toString()+" was found");
        }
        repository.deleteById(id);
        return true;
    }

    @Transactional
    public GETEncounterDetailDTO editMyEncounter(User user, PUTEncounterDTO encounterPutDTO){

        Encounter encounter = findEncounterFromStringId(encounterPutDTO.encounterId());

        UserData userData = userDataService.findUserDataFromUserId(user.getId().toString());

        if(!userData.getEncounters().contains(encounter)){
            throw new UnauthorizedEncounterAccessException("Este encuentro no es tuyo");
        }
        return encounterToEncounterDetailDTO(edit(encounterPutDTO, encounter));


    }

    public GETEncounterDetailDTO editAnyEncounter(PUTEncounterDTO encounterPutDTO){

        Encounter encounter = findEncounterFromStringId(encounterPutDTO.encounterId());
        return encounterToEncounterDetailDTO(edit(encounterPutDTO, encounter));


    }

    public Encounter edit(PUTEncounterDTO encounterPutDTO, Encounter encounter){

        encounter.setLocation(encounterPutDTO.location());
        encounter.setSpecie(specieService.getSpecieById(UUID.fromString(encounterPutDTO.specieId())));
        encounter.setDescription(encounterPutDTO.description());
        encounter.setMedias(encounterPutDTO.photos());

        return repository.save(encounter);


    }

    public GETEncounterDetailDTO encounterToEncounterDetailDTO(Encounter encounter){

        User user = userService.findUserById(UUID.fromString(encounter.getUserData().getUserId()));

        return GETEncounterDetailDTO.builder()
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

    public Encounter findEncounterFromStringId(String id){

        UUID uuid = service.stringToUUID(id);

        Optional<Encounter> encounterOptional = repository.findById(uuid);
        Encounter encounter;
        if(encounterOptional.isEmpty()){
            throw new EncounterNotFoundException();
        }else {
            return encounterOptional.get();
        }

    }


}
