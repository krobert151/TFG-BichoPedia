package com.robertorebolledonaharro.bichoapi.specie.dto;

import com.robertorebolledonaharro.bichoapi.article.dto.GETArticleDTO;
import lombok.Builder;

import java.util.List;
@Builder
public record SpecieDetailsDTO(
        String scientificName,
        String danger,
        String mainPhoto,
        List<GETArticleDTO> info,
        List<GETArticleDTO> identification,
        List<GETArticleDTO> cares



        ) {


}
