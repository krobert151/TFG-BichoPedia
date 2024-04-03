package com.robertorebolledonaharro.bichoapi.specie.service;

import com.robertorebolledonaharro.bichoapi.article.dto.ArticleDTO;
import com.robertorebolledonaharro.bichoapi.article.model.TypeOfArticle;
import com.robertorebolledonaharro.bichoapi.specie.dto.*;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieDangerIncorrectException;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieNotFoundException;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieScientificNameAlreadyExists;
import com.robertorebolledonaharro.bichoapi.specie.model.Danger;
import com.robertorebolledonaharro.bichoapi.specie.model.Specie;
import com.robertorebolledonaharro.bichoapi.specie.repo.SpecieRepository;
import com.robertorebolledonaharro.bichoapi.specie.specification.SpecieSpecification;
import com.robertorebolledonaharro.bichoapi.util.CriteriaParser;
import com.robertorebolledonaharro.bichoapi.util.GenericSpecificationsBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecieService {

    private final SpecieRepository repository;

    public Specie getSpecieById(UUID id){

        return repository.findById(id).get();

    }

    public List<SpeciesNameDTO> findAllNames(){
        return  repository.findAll().stream().map(x->{
            return SpeciesNameDTO.builder()
                    .id(x.getId().toString())
                    .name(x.getScientificName())
                    .build();
        }).toList();

    }


    public boolean findSpecieById(UUID id){
        return  repository.existsById(id);

    }

    public List<SpecieSimpleDTO> findSpeciesfromHome(int page, int count){
        Pageable pageable = PageRequest.of(page,count);
        Page<SpecieSimpleDTO> speciesPage = repository.findSpeciesInDangerOfExtintion(pageable);

        if(speciesPage.hasContent()){
            return speciesPage.getContent();

        }else {
            throw new SpecieNotFoundException("No Species was found on page "+page);
        }


    }

    public List<SpecieDTO> findAll(int page, int count){
        Pageable pageable = PageRequest.of(page, count);
        Page<Specie> specieList = repository.findAll(pageable);

        if (specieList.hasContent()) {
            return specieList.stream().map(specie -> {
                return SpecieDTO.builder()
                        .id(specie.getId())
                        .url(specie.getMedia() != null && !specie.getMedia().isEmpty()
                                ? specie.getMedia()
                                : "sebusca.jpg")
                        .type(specie.getType()!= null && !specie.getType().isEmpty()
                                ?specie.getType():
                                "undefined")
                        .danger(specie.getDanger() != null && !specie.getDanger().toString().isEmpty()
                                ? specie.getDanger().toString()
                                : "uncertain")
                        .scientificName(specie.getScientificName())
                        .build();
            }).toList();
        } else {
            throw new SpecieNotFoundException("No Species was found on page " + page);
        }
    }

    public List<SpecieDTO> findAllByAdvPredicate(String search) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<Specie> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<Specie> spec = specBuilder.build(parser.parse(search), SpecieSpecification::new);
        List<Specie> list = repository.findAll(spec);
        if(!list.isEmpty()) {
            return list.stream().map(SpecieDTO::of).toList();
        }else {
            throw new SpecieNotFoundException("No Species was found with "+search);
        }
    }


    public SpecieDTO updateDetails(SpeciePutDTO speciePutDTO) throws SpecieDangerIncorrectException {
        Optional<Specie> optionalSpecie = repository.findById(UUID.fromString(speciePutDTO.id()));

        if(optionalSpecie.isEmpty()){
            throw new SpecieNotFoundException("No Species was found");
        }

        Specie specie = optionalSpecie.get();


        if(!isDangerValid(speciePutDTO.danger())){
            throw new SpecieDangerIncorrectException(speciePutDTO.danger()+" is not a Danger type.");

        }

        if(repository.existsByScientificName(speciePutDTO.scientificName())){

            if (!speciePutDTO.scientificName().equalsIgnoreCase(specie.getScientificName())){

                throw new SpecieScientificNameAlreadyExists(speciePutDTO.scientificName() + " scientific name already exists");

            }

        }

        specie.setScientificName(speciePutDTO.scientificName());
        specie.setDanger(Danger.valueOf(speciePutDTO.danger()));
        specie.setMedia(speciePutDTO.mainPhoto());
        specie.setType(speciePutDTO.type());

        repository.save(specie);

        return SpecieDTO.builder()
                .id(specie.getId())
                .url(specie.getMedia() != null && !specie.getMedia().isEmpty()
                        ? specie.getMedia()
                        : "sebusca.jpg")
                .type(specie.getType()!= null && !specie.getType().isEmpty()
                        ?specie.getType():
                        "undefined")
                .danger(specie.getDanger() != null && !specie.getDanger().toString().isEmpty()
                        ? specie.getDanger().toString()
                        : "uncertain")
                .scientificName(specie.getScientificName())
                .build();


    }

    public boolean isDangerValid(String danger){
        boolean boo = false;
        for(Danger d : Danger.values()){
            if(d.name().equalsIgnoreCase(danger)){
                boo=true;
            }
        }

        return boo;

    }

    public SpecieDTO registerSpecie (SpeciePostDTO speciePostDTO) throws SpecieDangerIncorrectException {


        if(repository.existsByScientificName(speciePostDTO.scientificName())){

            throw new SpecieScientificNameAlreadyExists(speciePostDTO.scientificName() + " scientific name already exists");

        }

        if(!isDangerValid(speciePostDTO.danger())){
            throw new SpecieDangerIncorrectException(speciePostDTO.danger()+" is not a Danger type.");

        }

        Specie specie = Specie.builder()
                .scientificName(speciePostDTO.scientificName())
                .type(speciePostDTO.type())
                .danger(Danger.valueOf(speciePostDTO.danger()))
                .media(speciePostDTO.mainPhoto())
                .build();

        return SpecieDTO.of(repository.save(specie));

    }


    @Transactional
    public SpecieDetailsDTO getDetailsById(UUID id){

        Optional<Specie> optionalSpecie = repository.findById(id);

        if(optionalSpecie.isEmpty()){
            throw new SpecieNotFoundException("No Species was found");
        }

        Specie specie = optionalSpecie.get();


        return SpecieDetailsDTO.builder()
                .scientificName(specie.getScientificName())
                .danger(
                        specie.getDanger() != null && !specie.getDanger().toString().isEmpty()
                        ? specie.getDanger().toString()
                        : "uncertain"
                )
                .mainPhoto(
                        specie.getMedia() != null && !specie.getMedia().isEmpty()
                        ? specie.getMedia()
                        : "sebusca.jpg"
                )
                .info(
                        specie.getArticles()
                                .stream()
                                .filter(
                                        x->x.getTypeOfArticle().equals(TypeOfArticle.INFO)&&x.isApproved()
                                ).map(
                                        article -> ArticleDTO.builder()
                                            .title(article.getTitle())
                                            .description(article.getText())
                                            .archives(article.getMedias())
                                            .build()
                                ).toList()
                )
                .identification(

                        specie.getArticles()
                                .stream()
                                .filter(
                                        x->x.getTypeOfArticle().equals(TypeOfArticle.IDENTIFICATION)&&x.isApproved()
                                ).map(
                                        article -> ArticleDTO.builder()
                                                .title(article.getTitle())
                                                .description(article.getText())
                                                .archives(article.getMedias())
                                                .build()
                                ).toList()

                )
                .cares(
                        specie.getArticles()
                                .stream()
                                .filter(
                                        x->x.getTypeOfArticle().equals(TypeOfArticle.CARES)&&x.isApproved()
                                ).map(
                                        article -> ArticleDTO.builder()
                                                .title(article.getTitle())
                                                .description(article.getText())
                                                .archives(article.getMedias())
                                                .build()
                                ).toList()

                )
                .build();
    }





}
